package com.gexiao.miaosha.service.impl;

import com.gexiao.miaosha.entity.User;
import com.gexiao.miaosha.service.UserService;
import com.gexiao.miaosha.util.MD5Utils;
import com.gexiao.miaosha.util.UserUtils;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author : gexiao
 * @since : 2019/11/19 9:38
 */
@RunWith(SpringRunner.class)
@SpringBootTest
class UserServiceImplTest {

    @Autowired
    private UserService userService;

    @Test
    public void add() {
        User user = new User();
        user.setName("gexiao");
        user.setPassword(MD5Utils.getMD5("123"));
        user.setHead("1.jpg");
        user.setLast_login_time(LocalDateTime.now());
        user.setLogin_count(0);
        user.setCreateAndUpdateTime();
        boolean save = userService.save(user);
        Assert.assertTrue(save);
    }




    @Test
    @Transactional
    void mock() throws Exception {
        int count = 1;
//        List<User> users = new ArrayList<User>(count);
//        //生成用户
//        for (int i = 0; i < count; i++) {
//            User user = new User();
//            user.setId(13000000000L + i);
//            user.setName("user" + i);
//            user.setPassword(MD5Utils.getMD5("123"));
//            user.setLast_login_time(LocalDateTime.now());
//            user.setCreateAndUpdateTime();
//            users.add(user);
//        }
//        userService.saveBatch(users);
        List<User> users = userService.list();

        UserUtils.createUser(users,count);
    }
}