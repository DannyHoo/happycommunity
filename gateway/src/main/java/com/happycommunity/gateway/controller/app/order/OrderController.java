package com.happycommunity.gateway.controller.app.order;

import com.alibaba.fastjson.JSON;
import com.happycommunity.business.model.parameter.order.CreateOrderParameter;
import com.happycommunity.business.model.parameter.user.LoginParameter;
import com.happycommunity.business.model.parameter.user.RegisterParameter;
import com.happycommunity.business.model.result.order.CreateOrderResult;
import com.happycommunity.business.model.result.user.RegisterResult;
import com.happycommunity.business.service.order.OrderBusinessService;
import com.happycommunity.business.service.user.UserBusinessService;
import com.happycommunity.framework.common.model.dto.order.OrderDetailDTO;
import com.happycommunity.framework.common.model.dto.user.UserDTO;
import com.happycommunity.framework.common.model.result.ServiceResult;
import com.happycommunity.framework.core.util.BeanUtil;
import com.happycommunity.gateway.controller.AbstractController;
import com.happycommunity.gateway.request.app.order.CreateOrderRequest;
import com.happycommunity.gateway.request.app.user.RegisterRequest;
import com.happycommunity.gateway.response.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Danny
 * @Title: OrderController
 * @Description:
 * @Created on 2019-02-26 16:53:49
 */
@Controller
@RequestMapping("/app/order")
public class OrderController {

    @Autowired
    private UserBusinessService userBusinessService;

    @Autowired
    private OrderBusinessService orderBusinessService;

    @RequestMapping("/createOrder")
    @ResponseBody
    public ResponseData register(HttpServletRequest request, @RequestBody CreateOrderRequest createOrderRequest) {
        UserDTO userDTO = userBusinessService.findByUserName(new LoginParameter().setUserName(createOrderRequest.getUserName())).getData();
        List<OrderDetailDTO> orderDetailDTOList = createOrderRequest.getOrderDetailList();
        CreateOrderParameter createOrderParameter = new CreateOrderParameter()
                .setUserDTO(userDTO)
                .setOrderDetailDTOList(orderDetailDTOList);
        ServiceResult<CreateOrderResult> createOrderResult = orderBusinessService.createOrder(createOrderParameter);
        return new ResponseData(createOrderResult);
    }

    public static void main(String[] args) {
        List<OrderDetailDTO> orderDetailList = new ArrayList<>();
        orderDetailList.add(new OrderDetailDTO().setGoodsNo("1").setGoodsNum(2));
        CreateOrderRequest createOrderRequest = new CreateOrderRequest().setUserName("d").setOrderDetailList(orderDetailList);
        System.out.println(JSON.toJSONString(createOrderRequest));
    }
}
