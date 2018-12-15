package com.cyb.amqp;

import com.cyb.amqp.bean.Book;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Springboot01AmqpApplicationTests {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private AmqpAdmin amqpAdmin;

    /**
     * 创建交换机
     */
    @Test
    public void testCreateExchange () {
        DirectExchange exchange = new DirectExchange("amqpadmin.exchange");
        amqpAdmin.declareExchange(exchange);
        System.out.println("创建完成");
    }

    //创建队列
    @Test
    public void testCreateQueue () {
        Queue queue = new Queue("amqpadmin.queue", true);
        amqpAdmin.declareQueue(queue);
        System.out.println("创建完成");
    }

    //创建绑定规则  这一步要在上面两步执行完成后 才能进行绑定
    @Test
    public void testBinding () {
        Binding binding = new Binding("amqpadmin.queue", Binding.DestinationType.QUEUE, "amqpadmin.exchange", "amqp.hh", null);
        amqpAdmin.declareBinding(binding);
        System.out.println("绑定完成");
    }

    /**
     * 1、单播 点对点
     */
    @Test
    public void contextLoads () {
        //Message需要自己构造一个；定制消息体内容和消息头
        //rabbitTemplate.send(exchage,routingKey,message);

        //object默认当成消息体，只需要传入发送对象，自动序列化发送给rabbitmq
        //rabbitTemplate.convertAndSend(exchage,routingKey,object);
        Map<String, Object> map = new HashMap<>();
        map.put("msg", "这是第一个消息");
        map.put("data", Arrays.asList("helloworld", 123, false));
        map.put("book", new Book("xiyouji", "laowu"));
        //默认使用的是jdk的序列化器,可以配置自己的MessageConverter  注意需要在mq页面创建交换机和queue并设置好路由
        //如需要在代码总设置这些 可以通过AmqpAdmin来设置

        rabbitTemplate.convertAndSend("exchange.direct", "news", map);
    }

    //接受数据，如何将数据自动的
    @Test
    public void testReceive () {
        Object message = rabbitTemplate.receiveAndConvert("message");
        System.out.println(message.getClass());
        System.out.println(message);
    }

    /**
     * 测试广播
     */
    @Test
    public void testSendMsg () {
        //由于是fanout的交换机 可以不用设置路由键
        rabbitTemplate.convertAndSend("exchange.fanout", "", new Book("sanguoyanyi", "luoguanzhong"));
    }

}

