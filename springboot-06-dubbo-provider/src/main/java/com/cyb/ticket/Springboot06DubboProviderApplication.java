package com.cyb.ticket;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 关于dubbo和zookeeper
 *  ZooKeeper 是一个分布式的，开放源码的分布式应用程序协调服务。
 * 它是 一个为分布式应用提供一致性服务的软件，提供的功能包括：
 * 配置维护、 域名服务、分布式同步、组服务等
 *
 *  Dubbo是Alibaba开源的分布式服务框架，它最大的特点是按照分层的方式来架构，
 * 使用这种方式可以使各个层之间解耦合（或者最大限度地松耦 合）。从服务模型
 * 的角度来看，Dubbo采用的是一种非常简单的模型，要 么是提供方提供服务，要么
 * 是消费方消费服务，所以基于这一点可以抽象 出服务提供方（Provider）和服务
 * 消费方（Consumer）两个角色。
 *
 *
 * 1、将服务提供者注册到注册中心
 *      1、引入dubbo和zkclient相关依赖
 *      2、配置dubbo额扫描包和注册中心的地址
 *      3、使用@Service发布服务
 */
@SpringBootApplication
public class Springboot06DubboProviderApplication {

    public static void main (String[] args) {
        SpringApplication.run(Springboot06DubboProviderApplication.class, args);
    }

}

