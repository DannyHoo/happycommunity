package com.happycommunity.framework.mq.rocketmq.demo;

import com.alibaba.fastjson.JSON;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.MQProducer;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.apache.rocketmq.remoting.exception.RemotingException;

import java.io.UnsupportedEncodingException;

/**
 * @author Danny
 * @Title: AsyncProducer
 * @Description: Asynchronous transmission is generally used in response time sensitive business scenarios.
 * @Created on 2019-04-30 10:25:47
 */
public class AsyncProducer {
    public static void main(String[] args) throws UnsupportedEncodingException, MQClientException, RemotingException, InterruptedException {
        DefaultMQProducer mqProducer=new DefaultMQProducer("Group-002");
        mqProducer.setNamesrvAddr("39.106.124.34:9876");

        mqProducer.start();
        mqProducer.setRetryTimesWhenSendAsyncFailed(0);

        Thread.sleep(1000);

        for (int i=0;i<100;i++){
            final int index=i;
            Message message=new Message("Topic-002","Tag-002",("This is 【Async】 Message Content "+i).getBytes(RemotingHelper.DEFAULT_CHARSET));
            mqProducer.send(message, new SendCallback() {
                public void onSuccess(SendResult sendResult) {
                    System.out.println("【"+index+"】Send Successfully! "+ sendResult.toString());
                }

                public void onException(Throwable throwable) {
                    System.out.println("【"+index+"】Send Failed! "+ throwable.toString());
                }
            });
        }

        //mqProducer.shutdown();
    }
}
