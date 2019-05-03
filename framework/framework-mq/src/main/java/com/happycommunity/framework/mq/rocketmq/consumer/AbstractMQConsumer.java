package com.happycommunity.framework.mq.rocketmq.consumer;

import java.util.UUID;

public abstract class AbstractMQConsumer {
    public abstract boolean doConsume(Object messageContent);


    /**
     * 同一Group下，每次实例化Consumer，需要设置不同的instance name，否则会报错[The consumer group[***] has been created before, specify another name please.]
     * @return
     */
    protected abstract String getRocketMqUniqueInstanceName();

}
