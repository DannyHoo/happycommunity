package com.happycommunity.business.model.result.order;

import com.happycommunity.framework.common.model.dto.order.OrderDTO;
import com.happycommunity.framework.common.model.dto.order.OrderDetailDTO;
import com.happycommunity.framework.common.model.result.BaseResult;

import java.util.List;

/**
 * @author Danny
 * @Title: CreateOrderResult
 * @Description:
 * @Created on 2019-01-09 22:54:28
 */
public class CreateOrderResult extends BaseResult {

    private OrderDTO orderDTO;
    private List<OrderDetailDTO> orderDetailDTOList;

    public OrderDTO getOrderDTO() {
        return orderDTO;
    }

    public CreateOrderResult setOrderDTO(OrderDTO orderDTO) {
        this.orderDTO = orderDTO;
        return this;
    }

    public List<OrderDetailDTO> getOrderDetailDTOList() {
        return orderDetailDTOList;
    }

    public CreateOrderResult setOrderDetailDTOList(List<OrderDetailDTO> orderDetailDTOList) {
        this.orderDetailDTOList = orderDetailDTOList;
        return this;
    }
}
