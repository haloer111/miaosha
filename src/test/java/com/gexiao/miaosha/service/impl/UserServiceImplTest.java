package com.gexiao.miaosha.service.impl;

import com.gexiao.miaosha.entity.User;
import com.gexiao.miaosha.service.UserService;
import com.gexiao.miaosha.util.MD5Utils;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;

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
    void login(HttpServletResponse response) {
        User login = userService.login(response,1196604745242963970L, "123");
        Assert.assertNotNull(login);
    }

    @Test
    void login1(HttpServletResponse response) {
        User login = userService.login(response,1196604745242963970L, "222");
        Assert.assertFalse(login == null);
    }
}