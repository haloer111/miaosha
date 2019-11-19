package com.gexiao.miaosha.controller;

import com.gexiao.miaosha.common.ResultEntity;
import com.gexiao.miaosha.entity.User;
import com.gexiao.miaosha.form.LoginForm;
import com.gexiao.miaosha.service.UserService;
import com.gexiao.miaosha.util.UserUtils;
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
    @GetMapping("/hello2")
    public String hello2(Model model) {
        model.addAttribute("user", UserUtils.get());
        return "hello";
    }

    @RequestMapping("/login")
    public String login(HttpServletResponse response, @RequestParam Long id, @RequestParam String password) {
        userService.login(response, id, password);
        return "redirect:/hello";
    }

    @RequestMapping("/add")
    @ResponseBody
    public ResultEntity add(@RequestBody User user) {
        boolean save = userService.save(user);
        return ResultEntity.success(true);
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
