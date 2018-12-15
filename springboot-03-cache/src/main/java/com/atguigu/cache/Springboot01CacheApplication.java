package com.atguigu.cache;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;


/**
 * 测试缓存
 *  原理：CacheManager===Cache 缓存组件来实际给缓存中存取数据
 */
@SpringBootApplication
@EnableCaching
public class Springboot01CacheApplication {

    public static void main (String[] args) {
        SpringApplication.run(Springboot01CacheApplication.class, args);
    }
}
