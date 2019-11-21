package com.gexiao.miaosha.service;

import com.gexiao.miaosha.entity.OrderInfo;
import com.gexiao.miaosha.entity.vo.GoodsVo;

/**
 * @author : gexiao
 * @since : 2019/11/20 11:52
 */
public interface MiaoshaService {

    OrderInfo miaosha(GoodsVo goodsVo);
}
