package com.happycommunity.framework.cache;

import com.alibaba.fastjson.JSON;
import com.happycommunity.framework.core.util.StringUtil;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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
    public void getTest() throws InterruptedException {
        ValueOperations valueOperations=redisTemplate.opsForValue();


        Object object=valueOperations.get("00002c06-e176-4b61-9ffd-73be614172ed");
        System.out.println(JSON.toJSONString(object));
        Set<String> keys =new HashSet<String>();

        keys=redisTemplate.keys("**");
        System.out.println(keys.size());
        if (!keys.isEmpty()) {
            redisTemplate.delete(keys);
        }
    }

    @Test
    public void multipleTest(){
        try{
            final ValueOperations valueOperations=redisTemplate.opsForValue();
            ExecutorService threadPool= Executors.newFixedThreadPool(1000);
            for (int i=0;i<10000000;i++){
                threadPool.submit(new Runnable() {
                    public void run() {
                        valueOperations.set(UUID.randomUUID().toString(),StringUtil.getStringRandom(1000));
                        System.out.println("done");
                    }
                });
            }

            Thread.sleep(10000000);
            //threadPool.shutdown();
        }catch (Exception e){
            e.printStackTrace();
        }


    }
}
