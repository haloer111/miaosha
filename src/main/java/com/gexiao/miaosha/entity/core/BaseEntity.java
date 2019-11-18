package com.gexiao.miaosha.entity.core;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * @author : gexiao
 * @since : 2019/11/15 10:07
 */
@Getter
@Setter
public abstract class BaseEntity<T> {
    protected T id;
    protected LocalDateTime createTime;
    protected LocalDateTime updateTime;

    /**
     * 设置创建时间和更新时间，如果已经存在创建时间则只赋值更新时间
     */
    public void setCreateAndUpdateTime() {
        if (getCreateTime() == null) {
            setCreateTime(LocalDateTime.now());
            setUpdateTime(LocalDateTime.now());
        } else {
            setUpdateTime(LocalDateTime.now());
        }
    }
}
