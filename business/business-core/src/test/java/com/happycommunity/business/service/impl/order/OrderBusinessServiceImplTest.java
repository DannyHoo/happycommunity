package com.happycommunity.business.service.impl.order;

import com.alibaba.fastjson.JSON;
import com.happycommunity.business.AbstractTest;
import com.happycommunity.business.model.parameter.order.CreateOrderParameter;
import com.happycommunity.business.model.parameter.user.LoginParameter;
import com.happycommunity.business.model.result.order.CreateOrderResult;
import com.happycommunity.business.service.order.OrderBusinessService;
import com.happycommunity.business.service.user.UserBusinessService;
import com.happycommunity.framework.common.model.dto.order.OrderDetailDTO;
import com.happycommunity.framework.common.model.dto.user.UserDTO;
import com.happycommunity.framework.common.model.result.ServiceResult;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Danny
 * @Title: OrderBusinessServiceImplTest
 * @Description:
 * @Created on 2019-02-27 11:24:43
 */
public class OrderBusinessServiceImplTest extends AbstractTest {

    @Autowired
    private OrderBusinessService orderBusinessService;
    @Autowired
    private UserBusinessService userBusinessService;

    @Test
    public void createOrderTest() {
        CreateOrderParameter createOrderParameter = getCreateOrderParameter();
        ServiceResult<CreateOrderResult> serviceResult = orderBusinessService.createOrder(createOrderParameter);
        System.out.println(JSON.toJSONString(serviceResult));
    }

    private CreateOrderParameter getCreateOrderParameter() {
        UserDTO userDTO = userBusinessService.findByUserName(new LoginParameter().setUserName("Danny")).getData();
        List<OrderDetailDTO> orderDetailDTOList = new ArrayList<OrderDetailDTO>();
        orderDetailDTOList.add(new OrderDetailDTO().setGoodsNo("G20180614160305682062").setGoodsNum(2));
        orderDetailDTOList.add(new OrderDetailDTO().setGoodsNo("G20180614161040114149").setGoodsNum(1));
        return new CreateOrderParameter()
                .setUserDTO(userDTO)
                .setOrderDetailDTOList(orderDetailDTOList);
    }
}
