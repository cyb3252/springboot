package com.cyb.cache.service;

import com.cyb.cache.bean.Employee;
import com.cyb.cache.mapper.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

/**
 * @author cyb
 * @date 2018/11/24 - 15:10
 */
@Service
public class EmployeeService {

    @Autowired
    private EmployeeMapper employeeMapper;

    /**
     * 将方法的运行结果进行缓存；以后再要相同的数据，直接从缓存中获取，不用吊调用方法
     * CacheManager管理多个Cache组件，对缓存进行真正的crud操作在cache中，每一个缓存组件有自己唯一一个名字
     * <p>
     * 1、自动配置类；CacheAutoConfiguration
     * 2、缓存的配置类
     * org.springframework.boot.autoconfigure.cache.GenericCacheConfiguration
     * org.springframework.boot.autoconfigure.cache.JCacheCacheConfiguration
     * org.springframework.boot.autoconfigure.cache.EhCacheCacheConfiguration
     * org.springframework.boot.autoconfigure.cache.HazelcastCacheConfiguration
     * org.springframework.boot.autoconfigure.cache.InfinispanCacheConfiguration
     * org.springframework.boot.autoconfigure.cache.CouchbaseCacheConfiguration
     * org.springframework.boot.autoconfigure.cache.RedisCacheConfiguration
     * org.springframework.boot.autoconfigure.cache.CaffeineCacheConfiguration
     * org.springframework.boot.autoconfigure.cache.GuavaCacheConfiguration
     * org.springframework.boot.autoconfigure.cache.SimpleCacheConfiguration【默认】
     * org.springframework.boot.autoconfigure.cache.NoOpCacheConfiguration
     * 3、哪个配置类默认生效：SimpleCacheConfiguration；
     * 3.1.在引入redis之后，生效的配置类就是RedisCacheConfiguration，而SimpleCacheConfiguration会没有匹配。
     * 因为在SimpleCacheConfiguration类上标了一个注解@ConditionalOnMissingBean({CacheManager.class})
     * RedisCacheConfiguration类上也标了@ConditionalOnMissingBean({CacheManager.class})，从上面的顺序，可以得到simple比redis要迟，
     * 而reids判断成功后就会为该容器注册这个缓存管理组件ReidsCacheManager，那么后面的就不生效了。
     * 3.2ReidsCacheManager帮我们创建rediscache来作为缓存组件reidscache通过操作redis缓存数据
     * 3.3默认保存数据k-v都是object；利用序列化保存；如何保存json
     * 3.3.1引入redis的starter，cachemanager变为rediscachemanager；
     * 3.3.2默认创建的redisManager操作reids的时候使用的是RedisTemplate<Object, Object>
     * 3.3.3RedisTemplate<Object, Object>默认使用的是jdk的序列化机制
     * 3.3.4自定义cacheManager
     * 4、给容器中注册了一个CacheManager：ConcurrentMapCacheManager
     * <p>
     * 5、可以获取和创建ConcurrentMapCache类型的缓存组件；他的作用将数据保存在ConcurrentMap中；
     * <p>
     * <p>
     * 几个属性：
     * cahcenames、value:指定缓存组件的名字
     * key:缓存数据使用的key；可以用它来指定默认是使用方法参数的值
     * keyGenerator：key的生成器；可以自己指定key的生成器的组件id
     * cacheManager：指定缓存管理器：或则cachereslover指定获取解析器
     * condition：指定符合条件的情况下才缓存
     * unless:否定缓存，当unless指定条件为true，方法的返回值就不会被缓存
     * sync：是否使用异步模式
     *
     * @param id
     * @return 运行流程：
     * @cacheable 1、方法运行之前，先去查询cache（缓存组件），按照cacheNames指定的名字获取；
     * （cacheManager先获取相应的缓存），第一次获取缓存如果没有cache组件会自动创建。
     * <p>
     * 2、去Cache中查找缓存生成的；使用一个key，；默认的是使用方法的参数
     * key是按照某种策略生成的，默认是使用keygenerator生成的。默认的是simplekeygenerator生成key；
     * SimpleKeyGenerator生成key的默认策略；
     * 如果没有参数；key=new SimpleKey()；
     * 如果有一个参数：key=参数的值
     * 如果有多个参数：key=new SimpleKey(params)；
     * 3、没有查到缓存就调用目标方法
     * 4、将目标方法返回的结果，放进缓存
     */
    @Cacheable(cacheNames = {"emp"}/*,condition = "#id>0"*/)
    public Employee getEmployee (Integer id) {
        System.out.println(id);
        Employee employee = employeeMapper.getEmpById(id);
        return employee;
    }


    /**
     * @param employee
     * @return
     * @CahcePut 即调用方法，又更新缓存数据，同步更新缓存
     * 修改了数据库的某个数据，同事更新缓存
     * 运行时机：
     * 1.先调用目标方法
     * 2.将目标方法的结果缓存起来
     */
    @CachePut(value = "name", key = "#result.id")
    public Employee updateEmp (Employee employee) {
        System.out.println("updateEmp" + employee);
        employeeMapper.updateEmp(employee);
        return employee;
    }

    /**
     * @param id
     * @CacheEvict 缓存清除
     * key：指定要清除的数据
     * allEntries:清除所有缓存 默认为false
     * beforeInvocation默认为false；缓存清除是否在方法之前执行
     * 默认代表是在方法执行之后执行的
     * 区别在于如果是false，如果在方法中发生错误，就不会清空缓存。
     */
    @CacheEvict(value = "emp", key = "#id")
    public void deleteEmp (Integer id) {
        System.out.println("deleteEmp" + id);
        //employeeMapper.deleteEmpById(id);
    }

    @Caching(cacheable = {
            @Cacheable(value = "emp", key = "#lastName")},
            put = {
                    @CachePut(value = "emp", key = "#result.id"),
                    @CachePut(value = "emp", key = "#result.email")
            }
    )
    public Employee getEmpByLastName (String lastName) {
        return employeeMapper.getEmpByLastName(lastName);
    }
}
