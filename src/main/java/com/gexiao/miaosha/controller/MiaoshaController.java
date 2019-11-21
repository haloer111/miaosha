package com.gexiao.miaosha.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.gexiao.miaosha.common.ResultEntity;
import com.gexiao.miaosha.entity.MiaoshaOrder;
import com.gexiao.miaosha.entity.OrderInfo;
import com.gexiao.miaosha.entity.vo.GoodsVo;
import com.gexiao.miaosha.service.GoodsService;
import com.gexiao.miaosha.service.MiaoshaOrderService;
import com.gexiao.miaosha.service.MiaoshaService;
import com.gexiao.miaosha.util.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author : gexiao
 * @since : 2019/11/14 17:29
 */
@Controller
public class MiaoshaController {

    @Autowired
    private GoodsService goodsService;
    @Autowired
    private MiaoshaOrderService miaoshaOrderService;
    @Autowired
    private MiaoshaService miaoshaService;

    @RequestMapping("/miaosha/{goodsId}")
    @ResponseBody
    public ResultEntity miaosha(@PathVariable Long goodsId) {
        GoodsVo goodsVo = goodsService.getGoodsVoByGoodsId(goodsId);
        //判断库存
        Integer stock = goodsVo.getStockCount();
        if (stock == null || stock <= 0) {
            return ResultEntity.failure("库存不足");
        }

        Long userId = UserUtils.get().getId();
        //判断是否已经秒杀商品
        int count = miaoshaOrderService.count(new LambdaQueryWrapper<MiaoshaOrder>()
                .eq(MiaoshaOrder::getUserId, userId)
                .eq(MiaoshaOrder::getGoodsId, goodsId));
        if (count > 0) {
            return ResultEntity.failure("已经秒杀过该商品");
        }
        OrderInfo orderInfo = miaoshaService.miaosha(goodsVo);

        return ResultEntity.success(orderInfo);

    }
}
