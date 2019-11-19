package com.gexiao.miaosha.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.gexiao.miaosha.entity.core.BaseEntity;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author : gexiao
 * @since : 2019/11/15 10:05
 */
@Data
@TableName("user")
public class User extends BaseEntity<Long> {


    private String name;
    private String password;
    private String head;
    private LocalDateTime last_login_time;
    private Integer login_count;
}
