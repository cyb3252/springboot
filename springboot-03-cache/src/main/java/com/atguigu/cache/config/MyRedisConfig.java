package com.atguigu.cache.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;

/**
 * @author cyb
 * @date 2018/12/14 - 10:39
 */
@Configuration
public class MyRedisConfig {

    @Bean
    public RedisTemplate<Object, Object> myRedisTemplate (RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<Object, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        Jackson2JsonRedisSerializer<Object> serializer = new Jackson2JsonRedisSerializer<>(Object.class);
        redisTemplate.setDefaultSerializer(serializer);
        return redisTemplate;
    }

    //CacheManagerCustomizers可以来定制一些缓存规则
    @Bean
    public RedisCacheManager myRedisCacheManager (RedisTemplate<Object, Object> myRedisTemplate) {
        RedisCacheManager cacheManager = new RedisCacheManager(myRedisTemplate);
        //key多了一个前缀

        //使用前缀，默认会将CahceName作为key的前缀
        cacheManager.setUsePrefix(true);
        return cacheManager;
    }
}
