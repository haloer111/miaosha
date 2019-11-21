package com.gexiao.miaosha.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gexiao.miaosha.entity.Goods;
import com.gexiao.miaosha.entity.vo.GoodsVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 用户mapper
 *
 * @author : gexiao
 * @since : 2019/11/15 10:21
 */
@Mapper
public interface GoodsMapper extends BaseMapper<Goods> {
    List<GoodsVo> listGoodsVo();

    GoodsVo getGoodsVoByGoodsId(@Param("goodsId") long goodsId);
}
