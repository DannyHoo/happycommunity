package com.happycommunity.order.service;

import com.happycommunity.framework.common.model.dto.order.OrderDetailDTO;
import com.happycommunity.framework.common.model.result.ServiceResult;
import com.happycommunity.order.parameter.OrderDetailListParameter;

import java.util.List;

/**
 * @author Danny
 * @Title: OrderDetailService
 * @Description:
 * @Created on 2019-02-26 16:37:42
 */
public interface OrderDetailService {

    ServiceResult<List<OrderDetailDTO>> saveOrderDetailList(OrderDetailListParameter orderDetailListParameter);

}
