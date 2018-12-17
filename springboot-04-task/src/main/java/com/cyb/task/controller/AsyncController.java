package com.cyb.task.controller;

import com.cyb.task.service.AsyncService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author cyb
 * @date 2018/12/16 - 17:13
 */
@RestController
public class AsyncController {

    @Autowired
    private AsyncService asyncService;

    @GetMapping("/hello")
    public String hello () {
        asyncService.sayHello();
        return "success";
    }

}
