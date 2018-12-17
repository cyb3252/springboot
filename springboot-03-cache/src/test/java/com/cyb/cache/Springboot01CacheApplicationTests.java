package com.cyb.cache;

import com.cyb.cache.bean.Employee;
import com.cyb.cache.mapper.EmployeeMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Springboot01CacheApplicationTests {


    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private EmployeeMapper employeeMapper;

    @Autowired
    StringRedisTemplate stringRedisTemplate;    //操作k-v都是字符串类型的

    @Autowired
    RedisTemplate redisTemplate;    //k-v都是对象

    @Autowired
    RedisTemplate<Object, Object> myRedisTemplate;

    /**
     * reids 常见数据类型 String(字符串),Hash(哈希),List(列表),Set(集合),ZSet(有序集合)
     * <p>
     * stringRedisTemplate.opsForValue();[字符串]
     * stringRedisTemplate.opsForList();[列表]
     * stringRedisTemplate.opsForSet();[集合]
     * stringRedisTemplate.opsForHash();[散列]
     * stringRedisTemplate.opsForZSet();[有序集]
     */
    @Test
    public void testRedisString () {
        //给redis中保存数据  append是在原先基础上追加，而set如果以前有这个键就会把之前的覆盖
        stringRedisTemplate.opsForValue().append("msg", "hello");

        //从reids中取数据
        /*String msg = stringRedisTemplate.opsForValue().get("msg");
        System.out.println(msg);*/
    }

    @Test
    public void testRedisList () {
        //向redis中存一个List
        /*stringRedisTemplate.opsForList().leftPush("mylist","1");
        stringRedisTemplate.opsForList().leftPush("mylist","2");*/

        //从最左边弹出一个
        String s = stringRedisTemplate.opsForList().leftPop("mylist");
        System.out.println(s);
    }

    /**
     * 测试保存对象
     */
    @Test
    public void testRedisObject () {
        Employee empById = employeeMapper.getEmpById(1);
        //默认如果保存对象，使用jdk的序列化，序列化之后数据保存到redis中
        //1、将数据已json的方式保存
        //（1）自己将数据转成json格式存
        //（2）使用redisTemplate.setXXXSerializer来设置自己的序列化器
        logger.info(empById.toString());
        this.redisTemplate.setKeySerializer(new StringRedisSerializer());
        this.redisTemplate.setValueSerializer(new Jackson2JsonRedisSerializer<>(Employee.class));
        /*this.redisTemplate.setDefaultSerializer(new Jackson2JsonRedisSerializer<>(Employee.class));*/
        redisTemplate.opsForValue().set("emp45", empById);
    }

    /**
     * 上面这个测试方法 如果只设置默认的序列器是产生不了我们想要的效果 ，
     * Jackson2JsonRedisSerializer<Employee> serializer = new Jackson2JsonRedisSerializer<>(Employee.class);
     * redisTemplate.setDefaultSerializer(serializer); 这个问题是我想不明白的，有可能使他们的设置方式不相同
     * 而在容器中配置自己的redistemplate是可以达到我们的效果的
     */
    @Test
    public void testMyRedisTemplate () {
        Employee empById = employeeMapper.getEmpById(1);
        this.myRedisTemplate.opsForValue().set("emp4", empById);
        //"emp4":{"id":1,"lastName":"张三","email":"zhangsan","gender":1,"dId":20}
    }

    /**
     * 获取刚才存入的那个json对象，注意使用什么吧对象序列化，反序列化的时候就要使用一样的序列化器
     */
    @Test
    public void testRedisGetObject () {
        this.redisTemplate.setKeySerializer(new StringRedisSerializer());
        this.redisTemplate.setValueSerializer(new Jackson2JsonRedisSerializer<Employee>(Employee.class));
        Employee emp = (Employee) this.redisTemplate.opsForValue().get("emp");
        System.out.println(emp);
    }

    @Test
    public void contextLoads () {
        Employee empById = employeeMapper.getEmpById(1);
        System.out.println(empById);
    }

}
