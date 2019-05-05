package com.happycommunity.framework.mq.rocketmq.producer;

import com.alibaba.fastjson.JSON;
import com.happycommunity.framework.core.util.SerializeUtil;
import com.happycommunity.framework.mq.rocketmq.MQMessage;
import com.happycommunity.framework.mq.rocketmq.MQSendResult;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.MessageQueueSelector;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageQueue;
import org.apache.rocketmq.remoting.exception.RemotingException;

import java.io.IOException;
import java.util.List;

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
        //mqProducer.setSendMsgTimeout(10000); //超时时间
        mqProducer.start();
    }

    /**
     * 发送消息
     * <p>
     *  SEND_OK 消息収送成功
     *  FLUSH_DISK_TIMEOUT 消息収送成功,但是服务器刷盘超时,消息已经迕入服务器队列,只有此时服务器宕机,消息才会丢失
     *  FLUSH_SLAVE_TIMEOUT 消息发送成功,但是服务器同步到 Slave 时超时,消息已经迕入服务器队列,只有此时服务器宕机,消息才会丢失
     *  SLAVE_NOT_AVAILABLE 消息发送成功,但是此时 slave 不可用,消息已经进入入服务器队列,只有此时服务器宕机,消息才会丢失
     * <p>
     * 对于要求依赖发送顺序消息的应用,由亍顺序消息的局限性,可能会涉及到主备自劢切换问题,所以如果 sendresult 中的 status 字段不等亍 SEND_OK,就应该尝试重试。对其其他应用,则没有必要这样
     *
     * @param mqMessage
     * @return
     */
    public MQSendResult sendMessage(MQMessage mqMessage) throws InterruptedException, RemotingException, MQClientException, MQBrokerException, IOException {
        Message message = message = new Message(
                mqMessage.getMqTopicAndTag().getTopic(),
                mqMessage.getMqTopicAndTag().getTag(),
                SerializeUtil.serialize(mqMessage.getBizValue()));
        message.setKeys(mqMessage.getMsgUniqueNo());
        //mqProducer.send(message,2000);//发送消息超时时间为2秒，超过之后会根据设置的重试策略重试

        //本身内部支持重试，最多重试3次；如果发送失败,则轮转到下一个 Broker；这个方法的总耗时时间不超过 sendMsgTimeout 设置的值,默认 10s。
        SendResult sendResult = sendResult = mqProducer.send(message);

        MQSendResult mqSendResult = new MQSendResult()
                .setSendResult(sendResult);
        System.out.println("消息已发送，mqSendResult：" + JSON.toJSONString(mqSendResult) + "，key：" + message.getKeys());
        return mqSendResult;
    }

    /**
     * 发送有序消息
     * @param mqMessage
     * @return
     * @throws InterruptedException
     * @throws RemotingException
     * @throws MQClientException
     * @throws MQBrokerException
     * @throws IOException
     */
    public MQSendResult sendMessageInOrder(MQMessage mqMessage) throws InterruptedException, RemotingException, MQClientException, MQBrokerException, IOException {
        Message message = message = new Message(
                mqMessage.getMqTopicAndTag().getTopic(),
                mqMessage.getMqTopicAndTag().getTag(),
                SerializeUtil.serialize(mqMessage.getBizValue()));
        message.setKeys(mqMessage.getMsgUniqueNo());

        SendResult sendResult = mqProducer.send(message, new MessageQueueSelector() {
            public MessageQueue select(List<MessageQueue> list, Message message, Object o) {
                return null;
            }
        },mqMessage.getBizValue());

        MQSendResult mqSendResult = new MQSendResult()
                .setSendResult(sendResult);
        System.out.println("消息已发送，mqSendResult：" + JSON.toJSONString(mqSendResult) + "，key：" + message.getKeys());
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
