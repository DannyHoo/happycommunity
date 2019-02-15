package com.happycommunity.business.service.order;

import com.happycommunity.business.model.parameter.order.CreateOrderParameter;
import com.happycommunity.business.model.result.order.CreateOrderResult;
import com.happycommunity.framework.common.model.result.ServiceResult;

/**
 * @author Danny
 * @Title: OrderBusinessService
 * @Description:
 * @Created on 2018-12-21 16:06:03
 */
public interface OrderBusinessService {

    public ServiceResult<CreateOrderResult> createOrder(CreateOrderParameter createOrderParameter);

}
