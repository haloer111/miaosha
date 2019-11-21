package com.gexiao.miaosha.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gexiao.miaosha.entity.MiaoshaGoods;
import com.gexiao.miaosha.entity.vo.GoodsVo;

/**
 * @author : gexiao
 * @since : 2019/11/19 9:34
 */
public interface MiaoshaGoodsService extends IService<MiaoshaGoods> {
    /**
     * 扣减库存
     * @param goodsVo
     */
    void reduceStock(GoodsVo goodsVo);
}
