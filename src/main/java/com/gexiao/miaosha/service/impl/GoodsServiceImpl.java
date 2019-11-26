package com.gexiao.miaosha.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gexiao.miaosha.dao.GoodsMapper;
import com.gexiao.miaosha.entity.Goods;
import com.gexiao.miaosha.entity.vo.GoodsVo;
import com.gexiao.miaosha.redis.RedisOperate;
import com.gexiao.miaosha.redis.prefix.MiaoshaKey;
import com.gexiao.miaosha.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * @author : gexiao
 * @since : 2019/11/19 9:38
 */
@Service
public class GoodsServiceImpl extends ServiceImpl<GoodsMapper, Goods> implements GoodsService {

    @Autowired
    private RedisOperate redisOperate;

    @Override
    public List<GoodsVo> listGoodsVo() {
        List<GoodsVo> goodsVos = baseMapper.listGoodsVo();
        return Optional.ofNullable(goodsVos).orElse(Collections.emptyList());
    }

    @Override
    public GoodsVo getGoodsVoByGoodsId(long goodsId) {
        Integer stock = redisOperate.get(MiaoshaKey.getGoodsStock, String.valueOf(goodsId), Integer.class);
        GoodsVo vo = new GoodsVo();
        vo.setStockCount(stock);
        vo.setId(goodsId);
        return vo;
    }


}
