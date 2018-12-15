package com.cyb.amqp;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/**
 * 一、为什么要使用消息队列？
 * 1、系统解耦
 * 2、流量削峰
 * 3、异步处理
 * <p>
 * 二、消息服务中两个重要的概念。
 * 消息代理和目的地
 * 当消息发送者发送消息之后，将由消息代理接管，消息代理保证消息传递到指定目的地
 * <p>
 * 三、消息队列主要有两种形式
 * 1、queue队列：点对点消息通信 point-to-point
 * 2、topic主题：发布publish/订阅subscribe消息通信
 * <p>
 * 四、点对点式
 * 1、消息发送者将消息发送至队列，接受者从队列中接受消息，消息读取后移出队列。
 * 2、消息只有唯一的发送者和接受者，但是可以有多个接收者。
 * <p>
 * 五、发布订阅式
 * 1、发布者发送消息到主题，多个订阅者订阅这个主题，那么消息就会被多次消费
 * <p>
 * 六、JMS java消息服务 J2EE的一个规范
 * 1、基于JVM消息代理规范。ActiveMQ，HornetMQ是JMS实现
 * <p>
 * 七、AMQP
 * 1、高级消息队列协议，也是一个消息代理规范，兼容JMS
 * 2、RabbitMQ是AMQP的实现
 * <p>
 * 八、Spring支持
 * 1、spring-jms提供了对JMS的支持
 * 2、spring-rabbit提供了对Aop的支持
 * 3、@EnableJMS和@EnableRabbit开启支持
 * 4、@JmsTemplate(JMS)、@RabbitListener(AMQP)注解在方法上监听消息代理发布的消息
 * <p>
 * <p>
 * 自动配置
 * 1、RabbitAutoConfiguration
 * 2、有自动配置了链接工厂ConnectionFactory
 * 3、RabbitProperties封装了RabbitMQ的配置
 * 4、RabbitTemplate：给RabbitMQ发送和接受消息的 类似于JdbcTemplate、RedisTemplate
 * 5、AmqpAdmin：RabbitMQ系统管理功能组件；
 * AmqpAdmin：创建和删除 Queue、Exchange，Binding
 * 6、@EnableRabbit+@RabbitListener监听消息队列的内容
 * <p>
 * 使用场景
 * 例如有两个系统，一个是订单系统，一个是库存系统。为了解除系统之间的耦合情况可以使用消息队列
 */
@SpringBootApplication
//开启基于注解的rabbitmq
@EnableRabbit
public class Springboot01AmqpApplication {

    public static void main (String[] args) {
        SpringApplication.run(Springboot01AmqpApplication.class, args);
    }

}

