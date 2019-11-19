package com.gexiao.miaosha.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gexiao.miaosha.entity.User;

import javax.servlet.http.HttpServletResponse;

/**
 * @author : gexiao
 * @since : 2019/11/19 9:34
 */
public interface UserService extends IService<User> {

    /**
     * 登录
     * @param id
     * @param password
     * @return
     */
    User login(HttpServletResponse response,Long id, String password);

    /**
     * 根据token从redis中获取user信息
     * @param token
     * @param response
     * @return
     */
    User getByToken(String token, HttpServletResponse response);
}
