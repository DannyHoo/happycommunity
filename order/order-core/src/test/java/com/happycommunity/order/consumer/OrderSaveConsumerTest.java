package com.happycommunity.order.consumer;

import com.alibaba.fastjson.JSON;
import com.happycommunity.framework.mq.rocketmq.MQMessage;
import com.happycommunity.framework.mq.rocketmq.MQSendResult;
import com.happycommunity.framework.mq.rocketmq.MQTopicEnum;
import com.happycommunity.framework.mq.rocketmq.producer.MQProducer;
import com.happycommunity.order.AbstractTest;
import com.happycommunity.order.parameter.OrderParameter;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.client.producer.SendStatus;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;

public class OrderSaveConsumerTest extends AbstractTest {

    @Autowired
    private MQProducer mqProducer;

    @Test
    public void sendMessageTest() throws InterruptedException, IOException, RemotingException, MQClientException, MQBrokerException {
        MQMessage mqMessage=new MQMessage()
                .setMqTopicAndTag(MQTopicEnum.ORDER_SAVE)
                .setBizValue(new OrderParameter().setOrderNo("123456").setPayType(1).setUserName("胡丹尼").setActualPrice(BigDecimal.ONE));
        MQSendResult mqSendResult=mqProducer.sendMessage(mqMessage);
        System.out.println(JSON.toJSONString(mqSendResult));
        Assert.assertEquals(mqSendResult.getSendResult().getSendStatus(), SendStatus.SEND_OK);
        InputStreamReader is = new InputStreamReader(System.in);
    }
}
