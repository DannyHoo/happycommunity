package com.happycommunity.framework.lock;

import java.util.concurrent.TimeUnit;

/**
 * @author Danny
 * @Title: ZookeeperDistributeLock
 * @Description:
 * @Created on 2019-04-08 18:00:00
 */
public class ZookeeperDistributeLock extends AbstractDistributeLock {


    public void lock() throws Exception {

    }

    public boolean tryLock(long time, TimeUnit timeUnit) throws Exception {
        return false;
    }

    public void unLock() throws Exception {

    }
}
