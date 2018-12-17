package com.cyb.task;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableAsync //开启异步注解
@EnableScheduling //开启基于注解的定时任务
//@EnableAutoConfiguration
public class Springboot04TaskApplication {

    public static void main (String[] args) {
        SpringApplication.run(Springboot04TaskApplication.class, args);
    }

}

