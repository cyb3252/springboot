package com.cyb.task.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * @author cyb
 * @date 2018/12/16 - 17:12
 *
 * 大部分情况下我们都是通过同步的方式处理数据，但是在于第三方系统交互的时候，容易造成响应迟缓的情况
 * 在spring3.x之后，就已经内置了@Aynsc来解决这个问题
 */
@Service
public class AsyncService {
    //告诉Spring这是一个异步的方法 异步任务
    @Async
    public void sayHello(){
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("数据处理中");
    }
}
