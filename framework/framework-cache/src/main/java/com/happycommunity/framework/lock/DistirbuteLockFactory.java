package com.happycommunity.framework.lock;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Danny
 * @Title: DistirbuteLockFactory
 * @Description:
 * @Created on 2019-04-08 23:03:57
 */
public class DistirbuteLockFactory {

    private final static Map<DistirbuteLockType, DistributeLock> distributeLockCache = new HashMap<DistirbuteLockType, DistributeLock>();

    public DistributeLock getDistributeLock(DistirbuteLockType distirbuteLockType) {
        DistributeLock distributeLock = distributeLockCache.get(distirbuteLockType);
        if (distributeLock == null) {
            distributeLock = createDistributeLock(distirbuteLockType);
            distributeLockCache.put(distirbuteLockType,distributeLock);
        }
        return distributeLock;
    }

    private DistributeLock createDistributeLock(DistirbuteLockType distirbuteLockType) {
        switch (distirbuteLockType) {
            case ZOOKEEPER:
                return createZookeeperDistributeLock();
            case DATABASE:
                return createDatabaseDistributeLock();
            default:
                return createRedisDistirbuteLock();
        }
    }

    private DistributeLock createRedisDistirbuteLock() {
        return null;
    }

    private DistributeLock createZookeeperDistributeLock() {
        return null;
    }


    private DistributeLock createDatabaseDistributeLock() {
        return null;
    }

}
