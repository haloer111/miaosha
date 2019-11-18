package com.gexiao.miaosha.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.gexiao.miaosha.entity.core.BaseEntity;
import lombok.Data;

/**
 * @author : gexiao
 * @since : 2019/11/15 10:05
 */
@Data
@TableName("user")
public class User extends BaseEntity<Long> {


    private String name;
}
