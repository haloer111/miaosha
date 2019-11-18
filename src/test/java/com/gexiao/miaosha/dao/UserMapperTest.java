package com.gexiao.miaosha.dao;

import com.gexiao.miaosha.MiaoshaApplication;
import com.gexiao.miaosha.entity.User;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author : gexiao
 * @since : 2019/11/15 10:22
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = MiaoshaApplication.class)
class UserMapperTest  {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void addUser(){
        User user = new User();
        user.setName("222");
        user.setCreateAndUpdateTime();
        userMapper.insert(user);
    }
    @Test
    public void getUser(){

        User user = userMapper.selectById(1);
        Assert.assertNotNull(user);
    }
}