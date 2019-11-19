package com.gexiao.miaosha.interceptor;

import com.gexiao.miaosha.entity.User;
import com.gexiao.miaosha.redis.prefix.TokenKey;
import com.gexiao.miaosha.service.UserService;
import com.gexiao.miaosha.util.UserUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 用户登录拦截器
 *
 * @author : gexiao
 * @since : 2019/11/19 15:54
 */
@Component
public class LoginInterceptor implements HandlerInterceptor {
    private final UserService userService;

    @Autowired
    public LoginInterceptor(UserService userService) {
        this.userService = userService;
    }


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Cookie[] cookies = request.getCookies();
        String paramToken = request.getParameter(TokenKey.TOKEN);
        String cookieToken = null;
        for (Cookie cookie : cookies) {
            if (StringUtils.equals(cookie.getName(), TokenKey.TOKEN)) {
                cookieToken = cookie.getValue();
            }
        }
        String token = StringUtils.isBlank(paramToken) == true ? cookieToken : paramToken;
        User user = userService.getByToken(token, response);
        if (user != null) {
            request.setAttribute(UserUtils.USER, user);
            return true;
        }

        return false;
    }
}
