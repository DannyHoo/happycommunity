package com.happycommunity.order.consumer;

import com.alibaba.fastjson.JSON;
import com.happycommunity.framework.common.model.dto.order.OrderDTO;
import com.happycommunity.framework.common.model.result.ServiceResult;
import com.happycommunity.framework.mq.rocketmq.MQTopicEnum;
import com.happycommunity.framework.mq.rocketmq.consumer.BaseMQConsumer;
import com.happycommunity.order.config.SystemConfig;
import com.happycommunity.order.parameter.OrderParameter;
import com.happycommunity.order.service.OrderService;
import org.apache.rocketmq.client.exception.MQClientException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@ConfigurationProperties(prefix="system") //接收application.yml中的myProps下面的属性
public class OrderSaveConsumer extends BaseMQConsumer {

    private final String group = MQTopicEnum.ORDER_SAVE.getGroup();
    private final String topic = MQTopicEnum.ORDER_SAVE.getTopic();
    private final String tag = MQTopicEnum.ORDER_SAVE.getTag();

    @Autowired
    private OrderService orderService;

    @Autowired
    private SystemConfig systemConfig;

    public OrderSaveConsumer() {
    }

    @PostConstruct
    public void init(){
        try {
            setGroupAndNameSrvAddr(group,systemConfig.getMqConsumerNamesrvAddr());
            listen(topic, tag);
        } catch (MQClientException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean doConsume(Object messageContent) {
        OrderParameter orderParameter = null;
        try{
            orderParameter = (OrderParameter) messageContent;
            ServiceResult<OrderDTO> result = orderService.saveOrder(orderParameter);
            if (result.isSuccess()) {
                System.out.println("订单入库成功");
                return true;
            } else {
                System.out.println("订单入库失败:"+ JSON.toJSONString(orderParameter));
                return false;
            }
        }catch (Exception e){
            System.out.println("订单入库失败:"+ JSON.toJSONString(orderParameter));
            return false;
        }
    }

}
