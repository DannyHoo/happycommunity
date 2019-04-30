package com.happycommunity.framework.lock;

import com.happycommunity.framework.AbstractSpringTest;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.retry.ExponentialBackoffRetry;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.CountDownLatch;

/**
 * @author Danny
 * @Title: ZookeeperDistributeLockTest
 * @Description:
 * @Created on 2019-04-08 17:59:26
 */
public class ZookeeperDistributeLockTest/* extends AbstractSpringTest*/ {

    private static String lockPath = "/curator/lock";
    private static CuratorFramework client = CuratorFrameworkFactory.builder()
            .connectString("39.106.124.34:2181")
            .retryPolicy(new ExponentialBackoffRetry(1000, 3))
            .build();

    static {
        client.start();
    }

    public static void main(String[] args) throws Exception {
        final InterProcessMutex lock = getLock();
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss|SSS");

        for (int i = 1; i <= 50; i++) {
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
                        //lock.acquire();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    String orderNo = simpleDateFormat.format(new Date());

                    try {
                        /*if (finalI == 40) throw new Exception();*/
                        //lock.release();
                    } catch (Exception e) {
                        System.out.println("生成的第" + (finalI) + "个订单号时异常，没有释放锁");
                    }

                    System.out.println("生成的第" + (finalI) + "个订单号是：" + orderNo);
                }
            }).start();
        }
        System.out.println("1秒后开始并发生成订单号……");
        Thread.sleep(1000);
        countDownLatch.countDown();

    }

    /*public static void main(String[] args) throws Exception {
        //释放死锁默认40s超时时间
        InterProcessMutex mutex=getLock();
        mutex.acquire();
        //mutex.release();
    }*/
    public static InterProcessMutex getLock() throws Exception {
        return new InterProcessMutex(client, lockPath);
    }

    /*50个线程执行结果：随着争夺锁的线程越来越少，获取锁的速度也越来越快，Zookeeper单位时间内处理的线程越来越多
    *
        生成的第40个订单号是：17:05:26|372
        生成的第31个订单号是：17:05:27|089
        生成的第17个订单号是：17:05:27|859
        生成的第44个订单号是：17:05:28|282
        生成的第13个订单号是：17:05:29|067
        生成的第45个订单号是：17:05:29|171
        生成的第2个订单号是：17:05:29|425
        生成的第6个订单号是：17:05:29|599
        生成的第23个订单号是：17:05:30|022
        生成的第30个订单号是：17:05:30|181
        生成的第24个订单号是：17:05:30|675
        生成的第12个订单号是：17:05:31|308
        生成的第9个订单号是：17:05:31|922
        生成的第3个订单号是：17:05:32|056
        生成的第4个订单号是：17:05:32|747
        生成的第46个订单号是：17:05:32|933
        生成的第18个订单号是：17:05:33|489
        生成的第7个订单号是：17:05:33|675
        生成的第29个订单号是：17:05:33|856
        生成的第26个订单号是：17:05:34|239
        生成的第37个订单号是：17:05:34|424
        生成的第47个订单号是：17:05:34|473
        生成的第48个订单号是：17:05:34|530
        生成的第14个订单号是：17:05:35|032
        生成的第28个订单号是：17:05:35|302
        生成的第10个订单号是：17:05:35|370
        生成的第25个订单号是：17:05:35|694
        生成的第35个订单号是：17:05:35|733
        生成的第36个订单号是：17:05:35|837
        生成的第19个订单号是：17:05:36|041
        生成的第5个订单号是：17:05:36|195
        生成的第21个订单号是：17:05:36|248
        生成的第27个订单号是：17:05:36|408
        生成的第11个订单号是：17:05:36|722
        生成的第50个订单号是：17:05:36|753
        生成的第20个订单号是：17:05:36|780
        生成的第15个订单号是：17:05:36|816
        生成的第39个订单号是：17:05:36|906
        生成的第8个订单号是：17:05:36|932
        生成的第41个订单号是：17:05:36|952
        生成的第38个订单号是：17:05:37|051
        生成的第33个订单号是：17:05:37|119
        生成的第34个订单号是：17:05:37|159
        生成的第1个订单号是：17:05:37|175
        生成的第43个订单号是：17:05:37|191
        生成的第32个订单号是：17:05:37|240
        生成的第22个订单号是：17:05:37|279
        生成的第42个订单号是：17:05:37|304
        生成的第49个订单号是：17:05:37|328
        生成的第16个订单号是：17:05:37|348
    */

    /*1000个线程执行结果
    *
        生成的第619个订单号是：11:07:52|347
        生成的第597个订单号是：11:14:53|422
        生成的第554个订单号是：11:14:55|099
        生成的第664个订单号是：11:14:55|457
        生成的第580个订单号是：11:20:52|705
        生成的第669个订单号是：11:22:18|319
        生成的第561个订单号是：11:28:35|896
        生成的第658个订单号是：11:29:16|864
        生成的第581个订单号是：11:29:17|270
        生成的第564个订单号是：11:29:17|843
        生成的第667个订单号是：11:35:24|083
        生成的第640个订单号是：11:36:36|373
        生成的第553个订单号是：11:36:36|718
        卡死了……
    */


}
