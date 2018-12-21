package com.cyb.security;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/**
 * 应用程序最主要的两个区域是“认证和授权”（或者是访问控制）
 * <p>
 * 认证，是建立一个他声明的主体的过程（一个主体 一般是指用户，设备 或者一些可以在你的应用程序中执行动作的其他系统）
 * <p>
 * 授权，指确定一个主体是否允许在你的应用程序执行一个动作的过程，为了抵达需要授权的店，主体的身份已经有认证过程建立
 * <p>
 * 1、引入spring security
 * 2、编写spring security的配置类
 *  @EnableWebSecurity
 *  public class MySecurityConfig extends WebSecurityConfigurerAdapter {
 *  }
 * 3、
 */
@SpringBootApplication
public class Springboot05SecurityApplication {

    public static void main (String[] args) {
        SpringApplication.run(Springboot05SecurityApplication.class, args);
    }

}

