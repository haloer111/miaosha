package com.gexiao.miaosha.controller;

import com.gexiao.miaosha.common.ResultEntity;
import com.gexiao.miaosha.entity.User;
import com.gexiao.miaosha.form.LoginForm;
import com.gexiao.miaosha.service.UserService;
import com.gexiao.miaosha.util.UserUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

/**
 * @author : gexiao
 * @since : 2019/11/14 17:29
 */
@Controller
@Slf4j
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/hello")
    public String hello(Model model) {
        model.addAttribute("user", UserUtils.get());
        return "hello";
    }


    @RequestMapping("/login")
    @ResponseBody
    public ResultEntity login(HttpServletResponse response, @RequestParam Long id, @RequestParam String password) {
        String token = userService.login(response, id, password);
        return ResultEntity.success(token);
    }

    @RequestMapping("/add")
    @ResponseBody
    public ResultEntity add(@RequestBody User user) {
        boolean save = userService.save(user);
        return ResultEntity.success(true);
    }
    @RequestMapping("/info")
    @ResponseBody
    public ResultEntity info() {
        User user = UserUtils.get();
        log.info("当前用户: {}", user);
        return ResultEntity.success(user);
    }


    /**
     * 测试方法
     *
     * @param form
     * @return
     */
    @RequestMapping(value = "test/login1", method = RequestMethod.POST)
    @ResponseBody
    public ResultEntity login1(@Valid @RequestBody LoginForm form) {
        return ResultEntity.success();
    }
}
