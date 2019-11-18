package com.gexiao.miaosha.redis;

import com.gexiao.miaosha.entity.User;
import com.gexiao.miaosha.redis.prefix.UserKey;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : gexiao
 * @since : 2019/11/18 15:06
 */
@RunWith(SpringRunner.class)
@SpringBootTest
class RedisOperateTest {

    @Autowired
    private RedisOperate redisOperate;

    @Test
    void get() {
        String key1 = "1";
        List list = redisOperate.get(UserKey.getById, key1, List.class);
        System.out.println("gexiao = " + list);
    }

    @Test
    void set() {
        List<User> userList  = new ArrayList<>();
        User user = new User();
        user.setName("各校");
        user.setId(1l);
        user.setCreateAndUpdateTime();
        userList.add(user);
        User user1 = new User();
        user1.setName("各校");
        user1.setId(1l);
        user1.setCreateAndUpdateTime();
        userList.add(user1);
        redisOperate.set(UserKey.getById, "" + 1, userList);
    }


    @Test
    void getArray() {
        String key1 = "1";
        List list = redisOperate.getArray(UserKey.getById, key1, User.class);
        System.out.println("gexiao = " + list);

    }
}