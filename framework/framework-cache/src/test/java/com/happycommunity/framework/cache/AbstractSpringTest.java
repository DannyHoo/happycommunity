package com.happycommunity.framework.cache;

import com.alibaba.fastjson.JSON;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

/**
 * @author Danny
 * @Title: AbstractSpringTest
 * @Description:
 * @Created on 2019-02-19 18:13:57
 */
@ContextConfiguration(locations = {"classpath:/framework-redis-single.xml"})
public class AbstractSpringTest extends AbstractJUnit4SpringContextTests {

    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void getTest(){
        ValueOperations valueOperations=redisTemplate.opsForValue();
        valueOperations.set("a","A");
        Object object=valueOperations.get("a");
        System.out.println(JSON.toJSONString(object));
    }
}
