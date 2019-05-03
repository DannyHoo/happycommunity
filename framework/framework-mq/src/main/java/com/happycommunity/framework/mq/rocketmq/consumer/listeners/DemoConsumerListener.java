package com.happycommunity.framework.mq.rocketmq.consumer.listeners;

import com.alibaba.fastjson.JSON;
import com.happycommunity.framework.core.util.SerializeUtil;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.message.MessageExt;

import java.io.IOException;
import java.util.List;

public class DemoConsumerListener implements MessageListenerConcurrently {

    public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> list, ConsumeConcurrentlyContext consumeConcurrentlyContext) {
        System.out.println(Thread.currentThread().getName()+" Received Message："+list);
        for (MessageExt messageExt:list){
            String message= null;
            try {
                message = JSON.toJSONString(SerializeUtil.deSerialize(messageExt.getBody()));
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+" Received Message："+message);
        }
        return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
    }
}
