package com.gexiao.miaosha.controller;

import com.gexiao.miaosha.common.ResultEntity;
import com.gexiao.miaosha.entity.MiaoshaOrder;
import com.gexiao.miaosha.entity.OrderInfo;
import com.gexiao.miaosha.entity.vo.GoodsVo;
import com.gexiao.miaosha.redis.RedisOperate;
import com.gexiao.miaosha.redis.prefix.MiaoshaKey;
import com.gexiao.miaosha.service.GoodsService;
import com.gexiao.miaosha.service.MiaoshaOrderService;
import com.gexiao.miaosha.service.MiaoshaService;
import com.gexiao.miaosha.util.UserUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author : gexiao
 * @since : 2019/11/14 17:29
 */
@Controller
public class MiaoshaController implements InitializingBean {

    @Autowired
    private GoodsService goodsService;
    @Autowired
    private MiaoshaOrderService miaoshaOrderService;
    @Autowired
    private MiaoshaService miaoshaService;
    @Autowired
    private RedisOperate redisOperate;

    private Map<Long, Boolean> localMap = new HashMap<>();

    @Override
    public void afterPropertiesSet() throws Exception {
        List<GoodsVo> goodsVos = goodsService.listGoodsVo();
        if (CollectionUtils.isEmpty(goodsVos)) {
            return;
        }
        goodsVos.forEach(vo -> {
            redisOperate.set(MiaoshaKey.getGoodsStock, String.valueOf(vo.getId()), vo.getStockCount());
            localMap.put(Long.parseLong(String.valueOf(vo.getId())), false);
        });
    }

    @RequestMapping("/miaosha/{goodsId}")
    @ResponseBody
    public ResultEntity miaosha(@PathVariable Long goodsId) {
        GoodsVo goodsVo = goodsService.getGoodsVoByGoodsId(goodsId);
        //判断库存
        if (goodsVo == null) {
            return ResultEntity.failure("库存不足");
        }
        if (localMap.get(goodsId)) {
            return ResultEntity.failure("库存不足");
        }
        Long userId = UserUtils.get().getId();
        //判断是否已经秒杀商品
        MiaoshaOrder miaoshaOrder = miaoshaOrderService.getMiaoshaOrderByGoodsIdUserId(goodsId, userId);
        if (miaoshaOrder != null) {
            return ResultEntity.failure("已经秒杀过该商品");
        }
        //从redis中扣减库存
        Long decr = redisOperate.decr(MiaoshaKey.getGoodsStock, String.valueOf(goodsId));
        if (decr < 0) {
            localMap.put(goodsId, true);
            return ResultEntity.failure("库存不足");
        }
        OrderInfo orderInfo = miaoshaService.miaosha(goodsVo);

        return ResultEntity.success(orderInfo);

    }


}
