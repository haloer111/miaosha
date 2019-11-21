package com.gexiao.miaosha.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.gexiao.miaosha.entity.vo.GoodsVo;
import com.gexiao.miaosha.service.GoodsService;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @author : gexiao
 * @since : 2019/11/20 11:12
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class GoodsServiceImplTest {
    @Autowired
    private GoodsService goodsService;
    @Test
    void listGoodsVo() {
        List<GoodsVo> goodsVos = goodsService.listGoodsVo();
        System.out.println("goodsVos = " + JSONArray.toJSONString(goodsVos));
        Assert.assertNotNull(goodsVos);
    }

    @Test
    void getGoodsVoByGoodsId() {
        GoodsVo goodsVoByGoodsId = goodsService.getGoodsVoByGoodsId(1);
        System.out.println("goodsVoByGoodsId = " + goodsVoByGoodsId);
        Assert.assertNotNull(goodsVoByGoodsId);
    }
}