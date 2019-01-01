package com.cyb.user;

import com.cyb.user.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


/**
 * 测试的时候要注意自己的提供者是否一致启动着
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class Springboot06DubboConsumerApplicationTests {

    @Autowired
    UserService userService;
    @Test
    public void contextLoads () {
        userService.hello();
    }

}

