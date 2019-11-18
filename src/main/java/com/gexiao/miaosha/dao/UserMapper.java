package com.gexiao.miaosha.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gexiao.miaosha.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户mapper
 * @author : gexiao
 * @since : 2019/11/15 10:21
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
}
