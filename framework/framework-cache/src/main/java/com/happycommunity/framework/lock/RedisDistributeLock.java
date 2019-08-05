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

    public boolean lock(String lockKey) throws Exception {
        try {
            String currentLockValue = String.valueOf(System.currentTimeMillis() + LockConstant.LOCK_EXPIRE);
            Boolean lockResult = redisTemplate.opsForValue().setIfAbsent(lockKey, currentLockValue);
            if (lockResult){
                return true;
            }

            String lockValue=(String) redisTemplate.opsForValue().get(lockKey);
            //如果redis锁已经失效，当前线程尝试争夺redis锁（可能有多个线程同时争夺）
            if (lockValue!=null && Long.valueOf(lockValue)<System.currentTimeMillis()){
                String oldLockValue=(String) redisTemplate.opsForValue().getAndSet(lockKey,currentLockValue);
                if (lockValue.equals(oldLockValue)){
                    return true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean tryLock(String lockKey, long time, TimeUnit timeUnit) throws Exception {
        return false;
    }

    public boolean unLock(String lockKey) throws Exception {
        return false;
    }
}
