package com.happycommunity.framework.mq.rocketmq.demo;


import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.apache.rocketmq.remoting.exception.RemotingException;

import java.io.UnsupportedEncodingException;

/**
 * @author Danny
 * @Title: OnewayProducer
 * @Description: One-way transmission is used for cases requiring moderate reliability, such as log collection.
 * @Created on 2019-04-30 10:50:53
 */
public class OnewayProducer {
    public static void main(String[] args) throws MQClientException, UnsupportedEncodingException, RemotingException, InterruptedException, MQBrokerException {
        DefaultMQProducer mqProducer=new DefaultMQProducer("Group-001");
        mqProducer.setNamesrvAddr("39.106.124.34:9876");

        mqProducer.start();

        for (int i=0;i<1;i++){
            Message message=new Message("Topic-001","Tag-001",("This is 【SendOneWay】 Message Content "+i).getBytes(RemotingHelper.DEFAULT_CHARSET));
            mqProducer.sendOneway(message);
            System.out.println("finished");
        }

        //mqProducer.shutdown();
    }
}
