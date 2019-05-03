package com.happycommunity.framework.mq.rocketmq.producer;

import com.happycommunity.framework.core.util.SerializeUtil;
import com.happycommunity.framework.mq.rocketmq.MQMessage;
import com.happycommunity.framework.mq.rocketmq.MQSendResult;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;

import java.io.IOException;

public class MQProducer {
    private DefaultMQProducer mqProducer;
    private String namesrvAddr;
    private String group;

    /**
     * 初始化 Producer
     */
    public void init() throws MQClientException {
        mqProducer = new DefaultMQProducer();
        mqProducer.setProducerGroup(this.getGroup());
        mqProducer.setNamesrvAddr(this.getNamesrvAddr());
        //mqProducer.setRetryTimesWhenSendFailed(10);  //消息发送失败、发送超时时重试发送消息次数
        mqProducer.start();
    }

    /**
     * 发送消息
     *
     * @param mqMessage
     * @return
     */
    public MQSendResult sendMessage(MQMessage mqMessage) throws InterruptedException, RemotingException, MQClientException, MQBrokerException, IOException {
        Message message = message = new Message(
                    mqMessage.getMqTopicAndTag().getTopic(),
                    mqMessage.getMqTopicAndTag().getTag(),
                    SerializeUtil.serialize(mqMessage.getBizValue()));

        //mqProducer.send(message,2000);//发送消息超时时间为2秒，超过之后会根据设置的重试策略重试
        SendResult sendResult = sendResult = mqProducer.send(message);

        MQSendResult mqSendResult = new MQSendResult()
                .setSendResult(sendResult);
        return mqSendResult;
    }

    /**
     * 销毁 Producer
     */
    public void destroy() {
        if (mqProducer != null) {
            mqProducer.shutdown();
        }
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
}
