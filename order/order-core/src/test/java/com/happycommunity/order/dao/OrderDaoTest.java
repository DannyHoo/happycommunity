package com.happycommunity.order.dao;

import com.alibaba.fastjson.JSON;
import com.happycommunity.framework.core.util.RandomValueUtil;
import com.happycommunity.framework.core.util.StringUtil;
import com.happycommunity.order.AbstractTest;
import com.happycommunity.order.domain.OrderDO;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.*;

/**
 * @author Danny
 * @Title: OrderDaoTest
 * @Description:
 * @Created on 2019-01-04 10:39:13
 */
public class OrderDaoTest extends AbstractTest {

    @Autowired
    private OrderDAO orderDAO;

    @Test
    public void findAllTest() {
        long startTime = System.currentTimeMillis();
        List<OrderDO> orderDOList= orderDAO.findAll();
        System.out.println("结果："+JSON.toJSONString(orderDOList)+"\n 耗时："+(System.currentTimeMillis() - startTime));
    }

    @Test
    public void findTest() {
        long startTime = System.currentTimeMillis();
        OrderDO orderDO = orderDAO.findByOrderNo("WAJbR520190305221700770965");
        System.out.println("结果："+JSON.toJSONString(orderDO)+"\n 耗时："+(System.currentTimeMillis() - startTime));
    }

    @Test
    public void test() throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < 20; i++) {//20个线程
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int j = 0; j < 10; j++) {//每个线程执行100次
                        List<OrderDO> orderDOList = new ArrayList<>();
                        for (int i = 0; i < 3000; i++) {//每次插入1000条数据
                            orderDOList.add(getOrderDO());
                        }
                        long startTime = System.currentTimeMillis();
                        int result = orderDAO.insertOrderDOBatch(orderDOList);
                        System.out.println(Thread.currentThread().getName() + "原始数据条数"+orderDOList.size()+"，插入数据条数：" + result + "，耗时：" + (System.currentTimeMillis() - startTime));
                    }
                }
            });
            executorService.submit(thread);
        }
        Thread.currentThread().sleep(24 * 60 * 60 * 1000);
    }

    @Test
    public void test1() {
        List<OrderDO> orderDOList = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            orderDOList.add(getOrderDO());
        }
        long startTime = System.currentTimeMillis();
        int result = orderDAO.insertOrderDOBatch(orderDOList);
        System.out.println("插入数据：" + result + "，耗时：" + (System.currentTimeMillis() - startTime));
    }

    @Test
    public void insertOrderDOBatchTest() {
        /*OrderDO orderOD=orderDAO.findByOrderNo("4G698E20190104160810008575");
        System.out.println(JSON.toJSONString(orderOD));
*/
        long startTime = System.currentTimeMillis();
        int result = orderDAO.insertOrderDO(getOrderDO());
        System.out.println("插入数据：" + result + "，耗时：" + (System.currentTimeMillis() - startTime));
    }


    private volatile static int listSize = 20;
    private static Object lock = new Object();
    private volatile static boolean isNeedProduce = true;

    class Worker implements Runnable {
        @Override
        public void run() {
            while (true) {
                List<OrderDO> orderDOList = new ArrayList<>();
                for (int i = 0; i < 10000; i++) {
                    orderDOList.add(getOrderDO());
                }
                long startTime = System.currentTimeMillis();
                int result = orderDAO.insertOrderDOBatch(orderDOList);
                System.out.println("插入数据：" + result + "，耗时：" + (System.currentTimeMillis() - startTime));
            }
        }
    }

    class Producer implements Runnable {
        @Override
        public void run() {
            System.out.println("Producer start");
            while (true) {
                synchronized (lock) {
                    System.out.println("Producer get lock");
                    while (listSize == 1000) {
                        try {
                            System.out.println("容器已满，等待消费");
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    listSize = 1000;
                    System.out.println("1000条数据已经进入队列");
                    lock.notifyAll();
                }
            }
        }
    }

    private void save() {
        orderDAO.insertOrderDO(getOrderDO());
        listSize--;
    }

    class Consumer implements Runnable {
        @Override
        public void run() {
            System.out.println("Consumer start");
            while (true) {
                synchronized (lock) {
                    System.out.println("Consumer get lock");
                    while (listSize == 0) {
                        try {
                            System.out.println("容器已空，等待生产");
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    save();
                    if (listSize == 0) System.out.println("1000条数据已经保存");
                    lock.notifyAll();
                }
            }
        }
    }

    private OrderDO getOrderDO() {
        Map randomUserMap = RandomValueUtil.getAddress();
        return (OrderDO) new OrderDO()
                .setOrderNo(StringUtil.getStringRandom(6) + StringUtil.getRandomTimeStr())
                .setUserName(StringUtil.getStringRandom(8))
                .setReceiverName(randomUserMap.get("name").toString())
                .setReceiverMobileNo(randomUserMap.get("tel").toString())
                .setReceiverAddress(randomUserMap.get("road").toString())
                .setPayType(10)
                .setStatus(10)
                .setDeliverType(10)
                .setDeliverTime(10)
                .setTotalPrice(BigDecimal.valueOf(new Random().nextDouble()).multiply(BigDecimal.TEN))
                .setCutDownPrice(BigDecimal.valueOf(new Random().nextDouble()).multiply(BigDecimal.TEN))
                .setFreight(BigDecimal.valueOf(new Random().nextDouble()).multiply(BigDecimal.TEN))
                .setActualPrice(BigDecimal.valueOf(new Random().nextDouble()).multiply(BigDecimal.TEN))
                .setComment("这是测试订单");
    }


    static class MyQueue {
        BlockingQueue blockingQueue = new ArrayBlockingQueue(1000, true);

        public void exec() {

        }
    }
}
