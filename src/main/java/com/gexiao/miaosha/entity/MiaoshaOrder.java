package com.gexiao.miaosha.entity;

import com.gexiao.miaosha.entity.core.BaseEntity;
import lombok.Data;

@Data
public class MiaoshaOrder extends BaseEntity<Long> {
    private Long id;
    private Long userId;
    private Long orderId;
    private Long goodsId;
}
