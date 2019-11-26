package com.gexiao.miaosha.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.gexiao.miaosha.entity.MiaoshaGoods;
import com.gexiao.miaosha.entity.MiaoshaOrder;
import com.gexiao.miaosha.entity.OrderInfo;
import com.gexiao.miaosha.entity.User;
import com.gexiao.miaosha.entity.core.BaseEntity;
import com.gexiao.miaosha.entity.vo.GoodsVo;
import com.gexiao.miaosha.redis.RedisOperate;
import com.gexiao.miaosha.service.*;
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

    @Autowired
    private OrderService orderService;
    @Autowired
    private MiaoshaService miaoshaService;
    @Autowired
    private RedisOperate redisOperate;
    @Autowired
    private MiaoshaOrderService miaoshaOrderService;

    @Autowired
    private MiaoshaGoodsService miaoshaGoodsService;

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
        int count = 50;
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

        UserUtils.createUser(users, count);
    }

    @Test
    void reset() {

        MiaoshaGoods miaoshaGoods = new MiaoshaGoods();
        miaoshaGoods.setStockCount(50);

        miaoshaGoodsService.update(miaoshaGoods,
                new LambdaUpdateWrapper<MiaoshaGoods>()
                        .isNotNull(BaseEntity::getCreateTime)
        );

        miaoshaOrderService.remove(new LambdaQueryWrapper<MiaoshaOrder>().isNotNull(BaseEntity::getCreateTime));
        orderService.remove(new LambdaQueryWrapper<OrderInfo>().isNotNull(BaseEntity::getCreateTime));

    }

    @Test
    void miaosha() {
        for (int i = 50; i > 0; i--) {
            GoodsVo goodsVo = new GoodsVo();
            goodsVo.setStockCount(i);
            goodsVo.setId(1l);

            miaoshaService.miaosha(goodsVo);
        }

    }
}