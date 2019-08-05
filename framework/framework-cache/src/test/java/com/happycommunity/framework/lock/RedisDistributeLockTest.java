package com.happycommunity.framework.lock;

import com.happycommunity.framework.AbstractSpringTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import redis.clients.jedis.JedisPoolConfig;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.CountDownLatch;

/**
 * @author Danny
 * @Title: RedisDistributeLockTest
 * @Description:
 * @Created on 2019-04-08 17:59:37
 */
public class RedisDistributeLockTest /*extends AbstractSpringTest */ {

    private RedisTemplate redisTemplate;

    public RedisDistributeLockTest() {
        redisTemplate = new RedisTemplate();
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        RedisConnectionFactory redisConnectionFactory = new JedisConnectionFactory();
        ((JedisConnectionFactory) redisConnectionFactory).setHostName("39.106.124.34");
        ((JedisConnectionFactory) redisConnectionFactory).setPort(6379);
        ((JedisConnectionFactory) redisConnectionFactory).setPassword("requirepass");
        ((JedisConnectionFactory) redisConnectionFactory).setTimeout(2000);
        ((JedisConnectionFactory) redisConnectionFactory).setHostName("39.106.124.34");
        ((JedisConnectionFactory) redisConnectionFactory).afterPropertiesSet();
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new StringRedisSerializer());
        //初始化RedisTemplate
        redisTemplate.afterPropertiesSet();
    }

    public static void main(String[] args) throws InterruptedException {

        String v = "1554782839441";
        Long va = Long.valueOf(v);

        final String lockKey = "createOrder";
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss|SSS");
        RedisDistributeLockTest redisDistributeLock = new RedisDistributeLockTest();

        for (int i = 1; i <= 100; i++) {
            final int finalI = i;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        countDownLatch.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    try {
                        redisDistributeLock.lock(lockKey);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    String orderNo = simpleDateFormat.format(new Date());
                    System.out.println("生成的第" + (finalI) + "个订单号是：" + orderNo);

                    try {
                        if (finalI == 40) throw new Exception();
                        redisDistributeLock.unLock(lockKey);
                    } catch (Exception e) {
                        System.out.println("生成的第" + (finalI) + "个订单号时异常，没有释放锁");
                    }

                }
            }).start();
        }
        System.out.println("1秒后开始并发生成订单号……");
        Thread.sleep(1000);
        countDownLatch.countDown();
    }


    public void lock(String lockKey) {
        boolean isLocked = false;
        while (!isLocked) {
            isLocked = getLock(lockKey);
            if (isLocked) {
                return;
            }
        }
    }

    /**
     * 释放锁
     * 当前线程执行超时后，其他线程如果获取了锁，则可能会释放其他线程的锁
     * todo 升级使用watch监控key，用事务删除锁：https://www.cnblogs.com/liuyang0/p/6744076.html
     * @param lockKey
     */
    public void unLock(String lockKey) {
        redisTemplate.delete(lockKey);
    }

    public boolean getLock(String lockKey) {

        try {
            String currentLockValue = String.valueOf(System.currentTimeMillis() + LockConstant.LOCK_EXPIRE);
            Boolean lockResult = redisTemplate.opsForValue().setIfAbsent(lockKey, currentLockValue);

            /* 如果当前时间锁不存在，且获取锁成功 */
            if (lockResult) {
                return true;
            }

            //其他线程持有的锁已经过了失效时间，当前线程（可能有多个）重新争夺锁
            String lockValue = (String) redisTemplate.opsForValue().get(lockKey);
            if (lockValue != null && Long.valueOf(lockValue) < System.currentTimeMillis()) {
                String oldLockValue = (String) redisTemplate.opsForValue().getAndSet(lockKey, currentLockValue);
                if (lockValue.equals(oldLockValue)) {
                    return true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

}
