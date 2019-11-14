package com.gexiao.miaosha.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author : gexiao
 * @since : 2019/11/14 17:29
 */
@Controller
public class DemoController {

    @GetMapping("/")
    public String hello(){
        return "hello";
    }
}
