package com.happycommunity.framework.lock;

import java.util.concurrent.TimeUnit;

/**
 * @author Danny
 * @Title: DistributeLock
 * @Description:
 * @Created on 2019-04-08 17:57:35
 */
public interface DistributeLock {

    /**
     * 尝试获取锁，如果没有得到就等待
     *
     * @param lockKey
     * @throws Exception
     */
    boolean lock(String lockKey) throws Exception;

    /**
     * 尝试获取锁，直到超时
     *
     * @param lockKey
     * @param time
     * @param timeUnit
     * @return
     * @throws Exception
     */
    boolean tryLock(String lockKey, long time, TimeUnit timeUnit) throws Exception;

    /**
     * 释放锁
     *
     * @param lockKey
     * @throws Exception
     */
    boolean unLock(String lockKey) throws Exception;

}
