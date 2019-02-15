package com.happycommunity.goods.model.parameter;

import com.happycommunity.framework.common.model.parameter.BaseParameter;
import org.springframework.core.annotation.Order;

import java.util.List;

/**
 * @author Danny
 * @Title: OrderListParameter
 * @Description:
 * @Created on 2019-01-04 10:12:52
 */
public class OrderListParameter extends BaseParameter {
    private List<Order> orderList;

    public List<Order> getOrderList() {
        return orderList;
    }

    public OrderListParameter setOrderList(List<Order> orderList) {
        this.orderList = orderList;
        return this;
    }
}
