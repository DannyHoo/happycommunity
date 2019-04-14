package com.happycommunity.framework.core.util;

import java.lang.management.ManagementFactory;
import java.net.InetAddress;
import java.net.NetworkInterface;

/**
 * @author Danny
 * @Title: SnowFlakeUtils
 * @Description:
 * @Created on 2019-04-04 10:23:30
 */
public class SnowFlakeUtils {
    /**
     * 时间起始标记点，作为基准，一般取系统的最近时间（一旦确定不能变动）
     */
    private final static long TWEPOCH = 1288834974657L;
    /**
     * 机器标识位数
     */
    private final static long WORKER_ID_BITS = 5L;
    /**
     * 数据中心标识位数
     */
    private final static long DATACENTER_ID_BITS = 5L;
    /**
     * 机器ID最大值
     */
    private final static long MAX_WORKER_ID = -1L ^ (-1L << WORKER_ID_BITS);
    /**
     * 数据中心ID最大值
     */
    private final static long MAX_DATACENTER_ID = -1L ^ (-1L << DATACENTER_ID_BITS);
    /**
     * 毫秒内自增位
     */
    private final static long SEQUENCE_BITS = 12L;
    /**
     * 机器ID偏左移12位
     */
    private final static long WORKER_ID_SHIFT = SEQUENCE_BITS;
    /**
     * 数据中心ID左移17位
     */
    private final static long DATACENTER_ID_SHIFT = SEQUENCE_BITS + WORKER_ID_BITS;
    /**
     * 时间毫秒左移22位
     */
    private final static long TIMESTAMP_LEFT_SHIFT = SEQUENCE_BITS + WORKER_ID_BITS + DATACENTER_ID_BITS;
    /**
     *
     */
    private final static long SEQUENCE_MASK = -1L ^ (-1L << SEQUENCE_BITS);
    /**
     * 上次生产id时间戳
     */
    private static long lastTimestamp = -1L;
    /**
     * 0，并发控制
     */
    private long sequence = 0L;

    private final long workerId;
    /**
     * 数据标识id部分
     */
    private final long datacenterId;

    public SnowFlakeUtils() {
        this.datacenterId = getDatacenterId(MAX_DATACENTER_ID);
        this.workerId = getMaxWorkerId(datacenterId, MAX_WORKER_ID);
    }
    /**
     * @param workerId
     *            工作机器ID(0~31)
     * @param datacenterId
     *            序列号(0~31)
     */
    public SnowFlakeUtils(long workerId, long datacenterId) {
        if (workerId > MAX_WORKER_ID || workerId < 0) {
            throw new IllegalArgumentException(
                    String.format("worker Id can't be greater than %d or less than 0", MAX_WORKER_ID));
        }
        if (datacenterId > MAX_DATACENTER_ID || datacenterId < 0) {
            throw new IllegalArgumentException(
                    String.format("datacenter Id can't be greater than %d or less than 0", MAX_DATACENTER_ID));
        }
        this.workerId = workerId;
        this.datacenterId = datacenterId;
    }

    /**
     * 获取下一个ID(该方法线程安全)
     *
     * @return
     */
    public synchronized long nextId() {
        long timestamp = timeGen();
        //如果当前时间小于上一次ID生成的时间戳，说明系统时钟会退回这个时候应当抛出的异常
        if (timestamp < lastTimestamp) {
            throw new RuntimeException(String.format(
                    "Clock moved backwards.  Refusing to generate id for %d milliseconds", lastTimestamp - timestamp));
        }
        //如果同一时间生成的，则进行毫秒内序列
        if (lastTimestamp == timestamp) {
            // 当前毫秒内，则+1
            sequence = (sequence + 1) & SEQUENCE_MASK;
            //毫秒内序列溢出
            if (sequence == 0) {
                // 当前毫秒内计数满了，则等待下一秒
                timestamp = tilNextMillis(lastTimestamp);
            }
            //时间戳改变，毫秒内序列重置
        } else {
            sequence = 0L;
        }
        //上次生成ID的时间戳
        lastTimestamp = timestamp;
        // ID偏移组合生成最终的ID，并返回ID
        long nextId = ((timestamp - TWEPOCH) << TIMESTAMP_LEFT_SHIFT) | (datacenterId << DATACENTER_ID_SHIFT)
                | (workerId << WORKER_ID_SHIFT) | sequence;

        String id = String.valueOf(nextId);
        id = id.substring(6, id.length());
        nextId = Long.parseLong(id);
        return nextId;
    }

    /**
     * @descript: 阻塞到下一个毫秒，直到获得新的时间戳
     * @param:lastTimestamp 上次生成ID的时间截
     * @return: 当前时间戳
     **/
    private long tilNextMillis(final long lastTimestamp) {
        long timestamp = this.timeGen();
        while (timestamp <= lastTimestamp) {
            timestamp = this.timeGen();
        }
        return timestamp;
    }

    /**
     * @descript: 返回以毫秒为单位的当前时间
     * @param:
     * @return:  当前时间(毫秒)
     **/
    private long timeGen() {
        return System.currentTimeMillis();
    }

    /**
     * <p>
     * 获取 MAX_WORKER_ID
     * </p>
     */
    protected static long getMaxWorkerId(long datacenterId, long maxWorkerId) {
        StringBuffer mpid = new StringBuffer();
        mpid.append(datacenterId);
        String name = ManagementFactory.getRuntimeMXBean().getName();
        if (!name.isEmpty()) {
            /*
             * GET jvmPid
             */
            mpid.append(name.split("@")[0]);
        }
        /*
         * MAC + PID 的 hashcode 获取16个低位
         */
        return (mpid.toString().hashCode() & 0xffff) % (maxWorkerId + 1);
    }

    /**
     * <p>
     * 数据标识id部分
     * </p>
     */
    protected static long getDatacenterId(long maxDatacenterId) {
        long id = 0L;
        try {
            InetAddress ip = InetAddress.getLocalHost();
            NetworkInterface network = NetworkInterface.getByInetAddress(ip);
            if (network == null) {
                id = 1L;
            } else {
                byte[] mac = network.getHardwareAddress();
                id = ((0x000000FF & (long) mac[mac.length - 1])
                        | (0x0000FF00 & (((long) mac[mac.length - 2]) << 8))) >> 6;
                id = id % (maxDatacenterId + 1);
            }
        } catch (Exception e) {
            System.out.println(" getDatacenterId: " + e.getMessage());
        }
        return id;
    }

    //测试
    public static void main1(String[] args) {
        SnowFlakeUtils makerUtils = new SnowFlakeUtils();
        System.out.println(makerUtils.nextId());
    }

    public static void main(String[] args) {
        SnowFlakeUtils makerUtils = new SnowFlakeUtils(0,0);
        for (int i = 0;i<10;i++){
            long id = makerUtils.nextId();
            System.out.println(Long.toBinaryString(id));
            System.out.println(id);
        }
    }
}
