package com.happycommunity.framework.lock;

/**
 * @author Danny
 * @Title: LockConstant
 * @Description:
 * @Created on 2019-03-23 22:16:02
 */
public class LockConstant {

    /** 获取锁默认超时时间默认1分钟(单位毫秒) **/
    public final static long LOCK_TIMEOUT=Long.valueOf(System.getProperty("getLockTimeout","6000"));

    /** 锁过期时间默认1分钟(单位毫秒) **/
    public final static long LOCK_EXPIRE=Long.valueOf(System.getProperty("lockExpire","6000"));

}
