package com.gexiao.miaosha.entity.vo;

import com.gexiao.miaosha.entity.Goods;
import lombok.Data;
import lombok.ToString;

import java.util.Date;

/**
 * @author : gexiao
 * @since : 2019/11/20 10:54
 */
@Data
@ToString(callSuper = true)
public class GoodsVo extends Goods {
    private Integer stockCount;
    private Date startDate;
    private Date endDate;
    private Double miaoshaPrice;

}
