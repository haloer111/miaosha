package com.gexiao.miaosha.entity;

import com.gexiao.miaosha.entity.core.BaseEntity;
import lombok.Data;

import java.util.Date;

@Data
public class MiaoshaGoods extends BaseEntity<Long> {
    private Long id;
    private Long goodsId;
    private Double miaoshaPrice;
    private Integer stockCount;
    private Date startDate;
    private Date endDate;
}
