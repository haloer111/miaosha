package com.gexiao.miaosha.controller;

import com.gexiao.miaosha.common.ResultEntity;
import com.gexiao.miaosha.dao.UserMapper;
import com.gexiao.miaosha.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author : gexiao
 * @since : 2019/11/14 17:29
 */
@Controller
public class DemoController {

    @Autowired
    private UserMapper userMapper;

    @GetMapping("/")
    public String hello(Model model) {
        model.addAttribute("name","gexiao");
        return "hello";
    }

    @RequestMapping("/add")
    @ResponseBody
    public ResultEntity test1() {
        User user = new User();
        user.setId(null);
        user.setName("222");
        user.setCreateAndUpdateTime();
        userMapper.insert(user);
        return ResultEntity.success();
    }

    @RequestMapping("/{id}")
    @ResponseBody
    public ResultEntity test1(@PathVariable String id) {
        User user = userMapper.selectById(id);
        return ResultEntity.success(user);
    }


}
