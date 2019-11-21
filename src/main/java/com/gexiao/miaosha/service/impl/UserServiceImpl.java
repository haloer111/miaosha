package com.gexiao.miaosha.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gexiao.miaosha.dao.UserMapper;
import com.gexiao.miaosha.entity.User;
import com.gexiao.miaosha.exception.BusinessException;
import com.gexiao.miaosha.redis.RedisOperate;
import com.gexiao.miaosha.redis.prefix.TokenKey;
import com.gexiao.miaosha.service.UserService;
import com.gexiao.miaosha.util.MD5Utils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

/**
 * @author : gexiao
 * @since : 2019/11/19 9:38
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {


    @Autowired
    private RedisOperate redisOperate;

    @Override
    public String login(HttpServletResponse response, Long id, String password) {
        User byId = getById(id);
        if (MD5Utils.checkPassword(password, byId.getPassword())) {
            String uuid = UUID.randomUUID().toString();
            //写入cookies
            addCookieAndRedis(uuid, response, byId);
            return uuid;
        }

        throw new BusinessException("密码错误");
    }

    @Override
    public User getByToken(String token, HttpServletResponse response) {
        if (StringUtils.isBlank(token)) {
            throw new BusinessException("token不能为空");
        }
        User user = redisOperate.get(TokenKey.token, token, User.class);

        if (user != null) {
            //延长有效期
            addCookieAndRedis(token, response, user);
            return user;
        }
        return null;
    }

    /**
     * 写入cookie和redis缓存中
     *
     * @param token
     * @param response
     * @param user
     */
    private void addCookieAndRedis(String token, HttpServletResponse response, User user) {
        redisOperate.set(TokenKey.token, token, user);
        Cookie cookie = new Cookie(TokenKey.TOKEN, token);
        cookie.setMaxAge(TokenKey.token.expireSeconds());
        cookie.setPath("/");
        response.addCookie(cookie);
    }
}
