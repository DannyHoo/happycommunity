package com.happycommunity.order.parameter;

import com.happycommunity.framework.common.model.dto.order.OrderDetailDTO;
import com.happycommunity.framework.common.model.model.GlobalTraceData;
import com.happycommunity.framework.common.model.parameter.BaseParameter;

import java.util.List;

/**
 * @author Danny
 * @Title: OrderDetailListParameter
 * @Description:
 * @Created on 2019-02-26 16:38:05
 */
public class OrderDetailListParameter extends GlobalTraceData {

    private List<OrderDetailDTO> orderDetailDTOList;

    public List<OrderDetailDTO> getOrderDetailDTOList() {
        return orderDetailDTOList;
    }

    public OrderDetailListParameter setOrderDetailDTOList(List<OrderDetailDTO> orderDetailDTOList) {
        this.orderDetailDTOList = orderDetailDTOList;
        return this;
    }
}
