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
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;

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
    public void findTest(){
        OrderDO orderDO=orderDAO.findByOrderNo("u2c7JT20190104171236527653");
        System.out.println(JSON.toJSONString(orderDO));
    }

    @Test
    public void test() {
        /*ExecutorService executorService= Executors.newCachedThreadPool();
        executorService.submit(new Thread(new Worker()));
        executorService.shutdown();*/
        while(true){
            List<OrderDO> orderDOList=new ArrayList<>();
            for (int i=0;i<10000;i++){
                orderDOList.add(getOrderDO());
            }
            long startTime=System.currentTimeMillis();
            int result=orderDAO.insertOrderDOBatch(orderDOList);
            System.out.println("插入数据："+result+"，耗时："+(System.currentTimeMillis()-startTime));
        }
    }

    @Test
    public void insertOrderDOBatchTest() {
        /*OrderDO orderOD=orderDAO.findByOrderNo("4G698E20190104160810008575");
        System.out.println(JSON.toJSONString(orderOD));
*/
        long startTime=System.currentTimeMillis();
        int result=orderDAO.insertOrderDO(getOrderDO());
        System.out.println("插入数据："+result+"，耗时："+(System.currentTimeMillis()-startTime));
    }


    private volatile static int listSize=20;
    private static Object lock = new Object();
    private volatile static boolean isNeedProduce = true;

    class Worker implements Runnable{
        @Override
        public void run() {
            while(true){
                List<OrderDO> orderDOList=new ArrayList<>();
                for (int i=0;i<10000;i++){
                    orderDOList.add(getOrderDO());
                }
                long startTime=System.currentTimeMillis();
                int result=orderDAO.insertOrderDOBatch(orderDOList);
                System.out.println("插入数据："+result+"，耗时："+(System.currentTimeMillis()-startTime));
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
                    listSize=1000;
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
                    while (listSize==0) {
                        try {
                            System.out.println("容器已空，等待生产");
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    save();
                    if (listSize==0) System.out.println("1000条数据已经保存");
                    lock.notifyAll();
                }
            }
        }
    }

    private OrderDO getOrderDO() {
        Map randomUserMap = RandomValueUtil.getAddress();
        return new OrderDO()
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
                .setActualPrice(BigDecimal.valueOf(new Random().nextDouble()).multiply(BigDecimal.TEN));
    }
}
