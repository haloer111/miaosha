package com.gexiao.miaosha.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gexiao.miaosha.entity.OrderInfo;
import com.gexiao.miaosha.entity.vo.GoodsVo;

/**
 * @author : gexiao
 * @since : 2019/11/19 9:34
 */
public interface OrderService extends IService<OrderInfo> {

    /**
     * 创建订单
     * @param goodsVo
     * @return
     */
    OrderInfo create(GoodsVo goodsVo);
}
