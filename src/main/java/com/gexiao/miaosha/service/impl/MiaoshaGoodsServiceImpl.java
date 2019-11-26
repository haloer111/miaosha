package com.gexiao.miaosha.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gexiao.miaosha.dao.MiaoshaGoodsMapper;
import com.gexiao.miaosha.entity.MiaoshaGoods;
import com.gexiao.miaosha.entity.vo.GoodsVo;
import com.gexiao.miaosha.service.MiaoshaGoodsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author : gexiao
 * @since : 2019/11/19 9:38
 */
@Service
@Slf4j
public class MiaoshaGoodsServiceImpl extends ServiceImpl<MiaoshaGoodsMapper, MiaoshaGoods> implements MiaoshaGoodsService {

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void reduceStock(GoodsVo goodsVo) {
        MiaoshaGoods miaoshaGoods = new MiaoshaGoods();
        miaoshaGoods.setStockCount(goodsVo.getStockCount() - 1);
        log.info("进来的减库存方法，goodsId = {}",goodsVo.getId());
        baseMapper.update(miaoshaGoods,
                new LambdaQueryWrapper<MiaoshaGoods>()
                        .eq(MiaoshaGoods::getGoodsId,goodsVo.getId())
        );
    }

}
