package com.happycommunity.order.service.impl;

import com.happycommunity.framework.common.model.dto.order.OrderDTO;
import com.happycommunity.framework.common.model.enums.ResultStatusEnum;
import com.happycommunity.framework.common.model.result.ServiceResult;
import com.happycommunity.framework.core.util.BeanUtil;
import com.happycommunity.order.dao.OrderDAO;
import com.happycommunity.order.domain.OrderDO;
import com.happycommunity.order.parameter.OrderParameter;
import com.happycommunity.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

/**
 * @author Danny
 * @Title: OrderServiceImpl
 * @Description:
 * @Created on 2019-01-09 23:10:26
 */
@Service("orderService")
@com.alibaba.dubbo.config.annotation.Service(version = "1.0.0", interfaceClass = OrderService.class,filter = "dubboContextFilter")
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDAO orderDAO;

    @Override
    public ServiceResult<OrderDTO> saveOrder(OrderParameter orderParameter) {
        OrderDO orderDO= BeanUtil.convertIgnoreNullProperty(orderParameter,OrderDO.class);
        int result=orderDAO.insertOrderDO(orderDO);
        if (result==1 && orderDO.getId()!=null){
            OrderDTO orderDTO= BeanUtil.convertIgnoreNullProperty(orderDO,OrderDTO.class);
            return new ServiceResult<OrderDTO>(ResultStatusEnum.SUCCESS,orderDTO);
        }
        return new ServiceResult<OrderDTO>(ResultStatusEnum.FAILURE);

    }
}
