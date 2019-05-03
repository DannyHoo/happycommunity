package com.happycommunity.framework.mq.rocketmq;

import com.alibaba.fastjson.JSON;
import com.happycommunity.framework.core.util.StringUtil;
import com.happycommunity.framework.mq.AbstractSpringTest;
import com.happycommunity.framework.mq.rocketmq.producer.MQProducer;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MQProducerTest extends AbstractSpringTest {

    @Autowired
    private MQProducer mqProducer;

    @Test
    public void sendMessageTest() throws InterruptedException, IOException, RemotingException, MQClientException, MQBrokerException {
        MQMessage mqMessage=new MQMessage()
                .setMqTopicAndTag(MQTopicEnum.ORDER_SAVE)
                .setBizValue(new Order().setOrderNo("123456").setStatus("SUCCESS"));

        MQSendResult mqSendResult=mqProducer.sendMessage(mqMessage);

        System.out.println(JSON.toJSONString(mqSendResult));
    }

    static class Order implements Serializable {
        private String orderNo;
        private String status;

        public String getOrderNo() {
            return orderNo;
        }

        public Order setOrderNo(String orderNo) {
            this.orderNo = orderNo;
            return this;
        }

        public String getStatus() {
            return status;
        }

        public Order setStatus(String status) {
            this.status = status;
            return this;
        }
    }

}
