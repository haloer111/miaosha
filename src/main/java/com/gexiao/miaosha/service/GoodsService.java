package com.gexiao.miaosha.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gexiao.miaosha.entity.Goods;
import com.gexiao.miaosha.entity.vo.GoodsVo;

import java.util.List;

/**
 * @author : gexiao
 * @since : 2019/11/19 9:34
 */
public interface GoodsService extends IService<Goods> {
    List<GoodsVo> listGoodsVo();

    GoodsVo getGoodsVoByGoodsId(long goodsId);


}
