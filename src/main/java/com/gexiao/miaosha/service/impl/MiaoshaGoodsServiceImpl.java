package com.gexiao.miaosha.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gexiao.miaosha.dao.MiaoshaGoodsMapper;
import com.gexiao.miaosha.entity.MiaoshaGoods;
import com.gexiao.miaosha.entity.vo.GoodsVo;
import com.gexiao.miaosha.service.MiaoshaGoodsService;
import org.springframework.stereotype.Service;

/**
 * @author : gexiao
 * @since : 2019/11/19 9:38
 */
@Service
public class MiaoshaGoodsServiceImpl extends ServiceImpl<MiaoshaGoodsMapper, MiaoshaGoods> implements MiaoshaGoodsService {

    @Override
    public void reduceStock(GoodsVo goodsVo) {
        MiaoshaGoods miaoshaGoods = new MiaoshaGoods();
        miaoshaGoods.setStockCount(goodsVo.getStockCount() - 1);
        miaoshaGoods.setCreateAndUpdateTime();

        baseMapper.update(miaoshaGoods,
                new LambdaQueryWrapper<MiaoshaGoods>()
                        .eq(MiaoshaGoods::getGoodsId,goodsVo.getId())
                        .gt(MiaoshaGoods::getStockCount,0)
        );
    }

}
