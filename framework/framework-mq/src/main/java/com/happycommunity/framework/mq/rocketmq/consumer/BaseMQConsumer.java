package com.happycommunity.framework.mq.rocketmq.consumer;

import com.happycommunity.framework.core.util.DateUtils;
import com.happycommunity.framework.core.util.SerializeUtil;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.message.MessageExt;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public class BaseMQConsumer extends AbstractMQConsumer {
    protected DefaultMQPushConsumer mqPushConsumer;
    protected String group;
    protected String namesrvAddr;

    protected void setGroupAndNameSrvAddr(String group,String namesrvAddr) {
        mqPushConsumer = new DefaultMQPushConsumer();
        mqPushConsumer.setConsumerGroup(group);
        mqPushConsumer.setNamesrvAddr(namesrvAddr);
        mqPushConsumer.setInstanceName(getRocketMqUniqueInstanceName());
        //mqPushConsumer.setConsumeMessageBatchMaxSize(10);//每次最多拉取10条
        //mqPushConsumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);//设置Consumer第一次启动是从队列头部开始消费还是队列尾部开始消费，如果非第一次启动，那么按照上次消费的位置继续消费
    }

    /**
     * 具体消费者设置Topic和Tag，并且开始监听消息
     * 同一个group下的多个consumer实例监听的topic和tag要一致？
     * @param topic
     * @param tag
     * @throws MQClientException
     * t
     */
    public void listen(String topic, String tag) throws MQClientException {
        mqPushConsumer.subscribe(topic, tag);
        mqPushConsumer.registerMessageListener(new MessageListenerConcurrently() {
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> list, ConsumeConcurrentlyContext consumeConcurrentlyContext) {
                for (MessageExt messageExt : list) {
                    try {
                        Object object = SerializeUtil.deSerialize(messageExt.getBody());
                        //throw new Exception("消费异常，重新消费测试");
                        if (!doConsume(object)) {
                            return ConsumeConcurrentlyStatus.RECONSUME_LATER; //消费方消费失败，RocketMQ会重新投递消息
                        }
                    } catch (Exception e) {
                        System.out.println(DateUtils.getLongAllDateString(new Date())+" "+e.getMessage());
                        if (messageExt.getReconsumeTimes()==3){
                            System.out.println("消费异常超过3次，不再通过消息处理，记录报警日志，人工介入处理");
                            return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
                        }
                        return ConsumeConcurrentlyStatus.RECONSUME_LATER; //1s 5s 10s 30s 1m 2m 3m 4m 5m 6m 7m 8m 9m 10m 20m 30m 1h 2h
                    }
                }
                // 有异常抛出来，不要全捕获了，这样保证不能消费的消息下次重推，每次重新消费间隔：10s,30s,1m,2m,3m
                // 如果没有异常会认为都成功消费
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS; //消费方明确表示消费成功
            }
        });
        mqPushConsumer.start();
    }

    public String getNamesrvAddr() {
        return namesrvAddr;
    }

    public BaseMQConsumer setNamesrvAddr(String namesrvAddr) {
        this.namesrvAddr = namesrvAddr;
        return this;
    }

    public String getGroup() {
        return group;
    }

    public BaseMQConsumer setGroup(String group) {
        this.group = group;
        return this;
    }

    public void destroy() {
        if (mqPushConsumer != null) {
            mqPushConsumer.shutdown();
        }
    }

    protected String getRocketMqUniqueInstanceName(){
        return this.getClass().getName();
    }

    /**
     * 消费者的消费过程（业务逻辑）
     * 具体消费者需要重写
     *
     * @param messageContent
     * @return
     */
    public boolean doConsume(Object messageContent) {
        return Boolean.FALSE;
    }
}
