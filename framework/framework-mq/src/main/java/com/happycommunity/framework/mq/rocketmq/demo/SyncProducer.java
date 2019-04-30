package com.happycommunity.framework.mq.rocketmq.demo;

import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.apache.rocketmq.remoting.exception.RemotingException;

import java.io.UnsupportedEncodingException;

/**
 * @author Danny
 * @Title: SyncProducer
 * @Description: Reliable synchronous transmission is used in extensive scenes, such as important notification messages, SMS notification, SMS marketing system, etc..
 * @Created on 2019-04-29 21:56:41
 */
public class SyncProducer {

    public static void main(String[] args) throws MQClientException, RemotingException, InterruptedException, MQBrokerException, UnsupportedEncodingException {

        DefaultMQProducer mqProducer = new DefaultMQProducer("Group-002");
        mqProducer.setNamesrvAddr("39.106.124.34:9876");

        mqProducer.start();
        /*mqProducer.createTopic("Topic-001");*/
        for (int i = 0; i < 1; i++) {
            //Message message=new Message("Topic-001","Tag-001",("This is Message Content "+i).getBytes(RemotingHelper.DEFAULT_CHARSET));
            Message message = new Message("Topic-002", "Tag-002", ("This is 【Sync】 Message Content " + i).getBytes(RemotingHelper.DEFAULT_CHARSET));
            SendResult sendResult = mqProducer.send(message);
            System.out.println(sendResult);
        }

        mqProducer.shutdown();

    }
}
