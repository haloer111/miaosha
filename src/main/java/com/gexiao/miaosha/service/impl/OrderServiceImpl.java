package com.gexiao.miaosha.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gexiao.miaosha.dao.OrderMapper;
import com.gexiao.miaosha.entity.MiaoshaOrder;
import com.gexiao.miaosha.entity.OrderInfo;
import com.gexiao.miaosha.entity.vo.GoodsVo;
import com.gexiao.miaosha.redis.RedisOperate;
import com.gexiao.miaosha.redis.prefix.MiaoshaKey;
import com.gexiao.miaosha.service.MiaoshaOrderService;
import com.gexiao.miaosha.service.OrderService;
import com.gexiao.miaosha.util.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author : gexiao
 * @since : 2019/11/19 9:38
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, OrderInfo> implements OrderService {

    @Autowired
    private MiaoshaOrderService miaoshaOrderService;

    @Autowired
    private RedisOperate redisOperate;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public OrderInfo create(GoodsVo goodsVo) {
        Long userId = UserUtils.get().getId();

        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setUserId(userId);
        orderInfo.setGoodsId(goodsVo.getId());
        orderInfo.setDeliveryAddrId(0L);
        orderInfo.setGoodsName(goodsVo.getGoodsName());
        orderInfo.setGoodsCount(1);
        orderInfo.setGoodsPrice(goodsVo.getMiaoshaPrice());
        orderInfo.setOrderChannel(1);
        orderInfo.setStatus(0);
        orderInfo.setCreateAndUpdateTime();

        baseMapper.insert(orderInfo);

        Long orderId = orderInfo.getId();
        MiaoshaOrder miaoshaOrder = new MiaoshaOrder();
        miaoshaOrder.setUserId(userId);
        miaoshaOrder.setOrderId(orderId);
        miaoshaOrder.setGoodsId(goodsVo.getId());
        miaoshaOrder.setCreateAndUpdateTime();

        miaoshaOrderService.save(miaoshaOrder);

        String id = new StringBuilder().append(goodsVo.getId()).append(userId).toString();
        redisOperate.set(MiaoshaKey.hasMiaoshaOrderByGoodsIdUserId,id,miaoshaOrder);
        return orderInfo;
    }
}
