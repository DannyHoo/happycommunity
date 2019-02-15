package com.happycommunity.order.service;

import com.happycommunity.framework.common.model.dto.order.OrderDTO;
import com.happycommunity.framework.common.model.result.ServiceResult;
import com.happycommunity.order.parameter.OrderParameter;

/**
 * @author Danny
 * @Title: OrderService
 * @Description:
 * @Created on 2019-01-09 23:10:06
 */
public interface OrderService {
    public ServiceResult<OrderDTO> saveOrder(OrderParameter orderParameter);
}
