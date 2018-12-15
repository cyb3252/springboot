package com.atguigu.cache.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author cyb
 * @date 2018/11/25 - 13:47
 */
@Controller
public class HelloController {

    @RequestMapping("/hello")
    public String index(){
        return "index";
    }
}
