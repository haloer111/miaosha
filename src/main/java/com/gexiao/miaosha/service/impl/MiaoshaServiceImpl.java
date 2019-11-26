package com.gexiao.miaosha.service.impl;

import com.gexiao.miaosha.entity.OrderInfo;
import com.gexiao.miaosha.entity.vo.GoodsVo;
import com.gexiao.miaosha.service.GoodsService;
import com.gexiao.miaosha.service.MiaoshaGoodsService;
import com.gexiao.miaosha.service.MiaoshaService;
import com.gexiao.miaosha.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author : gexiao
 * @since : 2019/11/20 11:54
 */
@Service
@Slf4j
public class MiaoshaServiceImpl implements MiaoshaService {

    @Autowired
    private MiaoshaGoodsService miaoshaGoodsService;
    @Autowired
    private GoodsService goodsService;
    @Autowired
    private OrderService orderService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public OrderInfo miaosha(GoodsVo goodsVo) {
        log.info("执行秒杀前，GoodsVo={}",goodsVo);
        //扣减库存
        miaoshaGoodsService.reduceStock(goodsVo);
        //新增订单
        GoodsVo fill = goodsService.getGoodsVoByGoodsId(goodsVo.getId());
        OrderInfo order = orderService.create(fill);
        return order;
    }
}
