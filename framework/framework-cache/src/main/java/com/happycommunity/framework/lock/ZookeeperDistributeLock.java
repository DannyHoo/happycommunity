package com.happycommunity.framework.lock;

import java.util.concurrent.TimeUnit;

/**
 * @author Danny
 * @Title: ZookeeperDistributeLock
 * @Description:
 * @Created on 2019-04-08 18:00:00
 */
public class ZookeeperDistributeLock extends AbstractDistributeLock {

    public boolean lock(String lockKey) throws Exception {
        return false;
    }

    public boolean tryLock(String lockKey,long time, TimeUnit timeUnit) throws Exception {
        return false;
    }

    public boolean unLock(String lockKey) throws Exception {
        return false;
    }
}
