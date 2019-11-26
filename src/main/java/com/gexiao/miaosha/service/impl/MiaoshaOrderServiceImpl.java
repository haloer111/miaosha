package com.gexiao.miaosha.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gexiao.miaosha.dao.MiaoshaOrderMapper;
import com.gexiao.miaosha.entity.MiaoshaOrder;
import com.gexiao.miaosha.redis.RedisOperate;
import com.gexiao.miaosha.redis.prefix.MiaoshaKey;
import com.gexiao.miaosha.service.MiaoshaOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author : gexiao
 * @since : 2019/11/19 9:38
 */
@Service
public class MiaoshaOrderServiceImpl extends ServiceImpl<MiaoshaOrderMapper, MiaoshaOrder> implements MiaoshaOrderService {

    @Autowired
    private RedisOperate redisOperate;

    @Override
    public MiaoshaOrder getMiaoshaOrderByGoodsIdUserId(Long goodsId, Long userId) {
        String id = new StringBuilder().append(goodsId).append(userId).toString();
        MiaoshaOrder miaoshaOrder = redisOperate.get(MiaoshaKey.hasMiaoshaOrderByGoodsIdUserId, id, MiaoshaOrder.class);
        return miaoshaOrder;
    }
}
