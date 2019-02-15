package com.happycommunity.business.service.impl.order;

import com.happycommunity.business.model.parameter.order.CreateOrderParameter;
import com.happycommunity.business.model.result.order.CreateOrderResult;
import com.happycommunity.business.service.order.OrderBusinessService;
import com.happycommunity.framework.common.model.dto.order.OrderDTO;
import com.happycommunity.framework.common.model.result.ServiceResult;
import com.happycommunity.goods.service.GoodsService;
import com.happycommunity.order.parameter.OrderParameter;
import com.happycommunity.order.service.OrderService;
import com.happycommunity.user.service.UserService;
import org.springframework.stereotype.Service;

/**
 * @author Danny
 * @Title: OrderBusinessServiceImpl
 * @Description:
 * @Created on 2018-12-21 16:01:41
 */
@Service("orderBusinessService")
@com.alibaba.dubbo.config.annotation.Service(version = "1.0.0", interfaceClass = OrderBusinessService.class,filter = "dubboContextFilter")
public class OrderBusinessServiceImpl implements OrderBusinessService {

    private UserService userService;
    private GoodsService goodsService;
    private OrderService orderService;

    @Override
    public ServiceResult<CreateOrderResult> createOrder(CreateOrderParameter createOrderParameter) {

        

        OrderParameter orderParameter=new OrderParameter();
        ServiceResult<OrderDTO> saveOrderResult=orderService.saveOrder(orderParameter);

        return null;
    }
}
