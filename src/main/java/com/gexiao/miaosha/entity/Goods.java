package com.gexiao.miaosha.entity;

import com.gexiao.miaosha.entity.core.BaseEntity;
import lombok.Data;

@Data
public class Goods extends BaseEntity<Long> {
    private String goodsName;
    private String goodsTitle;
    private String goodsImg;
    private String goodsDetail;
    private Double goodsPrice;
    private Integer goodsStock;


}
