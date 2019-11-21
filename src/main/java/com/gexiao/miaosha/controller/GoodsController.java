package com.gexiao.miaosha.controller;

import com.gexiao.miaosha.common.ResultEntity;
import com.gexiao.miaosha.entity.vo.GoodsVo;
import com.gexiao.miaosha.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author : gexiao
 * @since : 2019/11/20 15:27
 */
@RestController
@RequestMapping("goods")
public class GoodsController {

    @Autowired
    private GoodsService goodsService;

    @GetMapping("list")
    public ResultEntity listGoodsVo() {
        List<GoodsVo> goodsVos = goodsService.listGoodsVo();
        return  ResultEntity.success(goodsVos);
    }
}
