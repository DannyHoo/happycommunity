package com.happycommunity.framework.lock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @author Danny
 * @Title: RedisDistributeLock
 * @Description:
 * @Created on 2019-04-08 17:59:55
 */
@Component
public class RedisDistributeLock extends AbstractDistributeLock {

    @Autowired
    private RedisTemplate redisTemplate;

    public void lock() throws Exception {



    }

    public boolean tryLock(long time, TimeUnit timeUnit) throws Exception {
        return false;
    }

    public void unLock() throws Exception {

    }
}
