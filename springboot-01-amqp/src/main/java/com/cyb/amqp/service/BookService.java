package com.cyb.amqp.service;

import com.cyb.amqp.bean.Book;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

/**
 * @author cyb
 * @date 2018/12/15 - 17:04
 */
@Service
public class BookService {

    @RabbitListener(queues = "message1")
    public void receive (Book book) {
        System.out.println("收到消息" + book);
    }

    //如果想要消息头的话
    @RabbitListener(queues = "message1")
    public void receive1 (Message message) {
        System.out.println(message.getBody());
        System.out.println(message.getMessageProperties());
    }
}
