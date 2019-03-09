package com.happycommunity.gateway.request.app.order;

import com.happycommunity.framework.common.model.dto.order.OrderDetailDTO;
import com.happycommunity.gateway.request.CommonRequest;

import java.util.List;

/**
 * @author Danny
 * @Title: CreateOrderRequest
 * @Description:
 * @Created on 2019-02-26 16:57:26
 */
public class CreateOrderRequest extends CommonRequest {

    private String userName;
    private List<OrderDetailDTO> orderDetailList;

    public String getUserName() {
        return userName;
    }

    public CreateOrderRequest setUserName(String userName) {
        this.userName = userName;
        return this;
    }

    public List<OrderDetailDTO> getOrderDetailList() {
        return orderDetailList;
    }

    public CreateOrderRequest setOrderDetailList(List<OrderDetailDTO> orderDetailList) {
        this.orderDetailList = orderDetailList;
        return this;
    }
}
