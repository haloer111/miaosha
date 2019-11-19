package com.gexiao.miaosha.util;

import com.gexiao.miaosha.entity.User;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @author : gexiao
 * @since : 2019/11/19 16:21
 */
public class UserUtils {
    public static final String USER = "user";

    public static User get() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        User user = (User) request.getAttribute(USER);
        return user;
    }
}
