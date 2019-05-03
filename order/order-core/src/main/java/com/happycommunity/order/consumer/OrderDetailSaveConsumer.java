package com.happycommunity.order.consumer;

import com.alibaba.fastjson.JSON;
import com.happycommunity.framework.common.model.dto.order.OrderDetailDTO;
import com.happycommunity.framework.common.model.result.ServiceResult;
import com.happycommunity.framework.core.util.BeanUtil;
import com.happycommunity.framework.mq.rocketmq.MQTopicEnum;
import com.happycommunity.framework.mq.rocketmq.consumer.BaseMQConsumer;
import com.happycommunity.order.config.SystemConfig;
import com.happycommunity.order.domain.OrderDO;
import com.happycommunity.order.parameter.OrderDetailListParameter;
import com.happycommunity.order.parameter.OrderParameter;
import com.happycommunity.order.service.OrderDetailService;
import org.apache.rocketmq.client.exception.MQClientException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
public class OrderDetailSaveConsumer extends BaseMQConsumer {

    private final String group = MQTopicEnum.ORDER_DETAIL_SAVE.getGroup();
    private final String topic = MQTopicEnum.ORDER_DETAIL_SAVE.getTopic();
    private final String tag = MQTopicEnum.ORDER_DETAIL_SAVE.getTag();

    @Autowired
    private OrderDetailService orderDetailService;

    @Autowired
    private SystemConfig systemConfig;

    public OrderDetailSaveConsumer() {
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
    public boolean doConsume(Object messageContent){
        OrderDetailListParameter orderDetailListParameter=null;
        try{
            orderDetailListParameter=(OrderDetailListParameter)messageContent;
            ServiceResult<List<OrderDetailDTO>> result= orderDetailService.saveOrderDetailList(orderDetailListParameter);
            if (result.isSuccess()){
                System.out.println("订单明细入库成功");
                return true;
            }else{
                System.out.println("订单明细入库失败："+ JSON.toJSONString(orderDetailListParameter));
                return false;
            }
        }catch (Exception e){
            System.out.println("订单明细入库失败："+ JSON.toJSONString(orderDetailListParameter));
            e.printStackTrace();
            return false;
        }

    }
}
