package com.happycommunity.framework.mq.rocketmq;

import com.alibaba.fastjson.JSON;
import org.apache.rocketmq.common.message.Message;

import java.io.Serializable;

public class MQMessage {
    private MQTopicAndTag mqTopicAndTag;
    private Object bizValue;

    public MQTopicAndTag getMqTopicAndTag() {
        return mqTopicAndTag;
    }

    public MQMessage setMqTopicAndTag(MQTopicAndTag mqTopicAndTag) {
        this.mqTopicAndTag = mqTopicAndTag;
        return this;
    }

    public Object getBizValue() {
        return bizValue;
    }

    public MQMessage setBizValue(Object bizValue) {
        this.bizValue = bizValue;
        return this;
    }
}
