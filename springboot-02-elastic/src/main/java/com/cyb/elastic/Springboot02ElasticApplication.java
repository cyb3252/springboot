package com.cyb.elastic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/**
 * 官方参考文档链接：https://www.elastic.co/guide/cn/elasticsearch/guide/current/index.html
 * <p>
 * 所有自动配置的类在spring-boot-autoconfigure-1.5.17.RELEASE.jar!\META-INF\spring.factories下
 * <p>
 * ElasticSearch是一个分布式搜索服务，提供restfulApi 底层基于Lucene，采用多shard（分片）的方式保证
 * 数据的安全，并且提供自动resharding的功能
 * <p>
 * springboot默认支持两种技术来与ES交互
 * 1、Jest(默认不生效)
 * 需要导入jest的工具包(import io.searchbox.client.JestClient;)
 * 2、SpringData ElasticSearch【es版本有可能不合适】
 * 版本适配说明：https://github.com/spring-projects/spring-data-elasticsearch
 * 如果版本不适配：2.4.6
 * 1）、升级SpringBoot版本
 * 2）、安装对应版本的ES   我这里采用的是第二种方法
 * 1、docker pull registry.docker-cn.com/library/elasticsearch:2.4.6
 * 2、docker images   得到2.4.6               5e9d896dc62c        3 months ago        479 MB
 * 3、docker run -e ES_JAVA_OPTS="-Xms256m -Xmx256m" -d -p 9201:9200 -p 9301:9300 --name es02 5e9d896dc62c
 * --虚拟机9200端口 刚才我们已经用掉了 所以这里把两个端口加一
 * 4、docker ps 查看是否启动成功
 * 这里并不能解决问题
 * NoNodeAvailableException[None of the configured nodes are available: [{#transport#-1}{192.168.1.5}{192.168.1.5:9301}]
 * 自动配置了以下内容
 * 2.1、ElasticsearchAutoConfiguration下的Client 节点信息ElasticsearchProperties下的clusterNodes和clusterName
 * 2.2、ElasticsearchTemplate来为我们提供模板操作es
 * 2.3、ElasticsearchRepository类似于jpa规范一样，继承这个接口，那么他就有这个接口以及他父接口
 * 相应的方法
 * <p>
 * Client 节点信息clusterNodes；clusterName
 * 1、ElasticsearchTemplate 操作es
 * 2、编写一个 ElasticsearchRepository 的子接口来操作ES；
 * 两种用法：https://github.com/spring-projects/spring-data-elasticsearch
 * 1、编写一个 ElasticsearchRepository
 */
@SpringBootApplication
public class Springboot02ElasticApplication {

    public static void main (String[] args) {
        SpringApplication.run(Springboot02ElasticApplication.class, args);
    }

}

