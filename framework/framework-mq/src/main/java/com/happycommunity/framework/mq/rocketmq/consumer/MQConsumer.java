package com.happycommunity.framework.mq.rocketmq.consumer;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;

public class MQConsumer {
    private DefaultMQPushConsumer mqPushConsumer;
    private MessageListenerConcurrently messageListener;
    private String namesrvAddr;
    private String group;
    private String topic;
    private String tag;

    public void init() throws MQClientException {
        mqPushConsumer = new DefaultMQPushConsumer();
        mqPushConsumer.setConsumerGroup(this.getGroup());
        mqPushConsumer.setNamesrvAddr(this.getNamesrvAddr());
        //mqPushConsumer.setConsumeMessageBatchMaxSize(10);//每次拉取10条
        //mqPushConsumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);//设置Consumer第一次启动是从队列头部开始消费还是队列尾部开始消费，如果非第一次启动，那么按照上次消费的位置继续消费
        mqPushConsumer.subscribe(this.getTopic(), this.getTag());
        mqPushConsumer.registerMessageListener(messageListener);
        mqPushConsumer.start();
    }

    public void destroy() {
        if (mqPushConsumer != null) {
            mqPushConsumer.shutdown();
        }
    }

    public MessageListenerConcurrently getMessageListener() {
        return messageListener;
    }

    public MQConsumer setMessageListener(MessageListenerConcurrently messageListener) {
        this.messageListener = messageListener;
        return this;
    }

    public String getNamesrvAddr() {
        return namesrvAddr;
    }

    public void setNamesrvAddr(String namesrvAddr) {
        this.namesrvAddr = namesrvAddr;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public DefaultMQPushConsumer getMqPushConsumer() {
        return mqPushConsumer;
    }

    public MQConsumer setMqPushConsumer(DefaultMQPushConsumer mqPushConsumer) {
        this.mqPushConsumer = mqPushConsumer;
        return this;
    }

    public String getTopic() {
        return topic;
    }

    public MQConsumer setTopic(String topic) {
        this.topic = topic;
        return this;
    }

    public String getTag() {
        return tag;
    }

    public MQConsumer setTag(String tag) {
        this.tag = tag;
        return this;
    }
}
