package com.happycommunity.framework.cache.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

/**
 * @author Danny
 * @Title: RedisCache
 * @Description:
 * @Created on 2019-02-19 17:34:35
 */
@Component
public class RedisCache implements ICache{

    @Autowired
    private RedisTemplate redisTemplate;


    public Object get(Object key) {
        ValueOperations valueOperations=redisTemplate.opsForValue();
        valueOperations.get(key);
        return null;
    }
}
