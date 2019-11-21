package com.gexiao.miaosha.service.impl;

import com.gexiao.miaosha.entity.OrderInfo;
import com.gexiao.miaosha.entity.vo.GoodsVo;
import com.gexiao.miaosha.service.MiaoshaGoodsService;
import com.gexiao.miaosha.service.MiaoshaService;
import com.gexiao.miaosha.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author : gexiao
 * @since : 2019/11/20 11:54
 */
@Service
public class MiaoshaServiceImpl implements MiaoshaService {

    @Autowired
    private MiaoshaGoodsService miaoshaGoodsService;
    @Autowired
    private OrderService orderService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public OrderInfo miaosha(GoodsVo goodsVo) {
        //扣减库存
        miaoshaGoodsService.reduceStock(goodsVo);
        //新增订单
        OrderInfo order = orderService.create(goodsVo);
        return order;
    }
}
