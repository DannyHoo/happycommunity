package com.happycommunity.framework.lock;

/**
 * @author Danny
 * @Title: DistirbuteLockType
 * @Description:
 * @Created on 2019-04-08 23:07:57
 */
public enum DistirbuteLockType {

    REDIS,
    ZOOKEEPER,
    DATABASE;

    /**
     * 默认REDIS
     * */
    public static DistirbuteLockType getValueOf(String name){
        try {
            return DistirbuteLockType.valueOf(name);
        }catch (Exception e){
            return DistirbuteLockType.REDIS;
        }
    }

}
