package com.happycommunity.business.model.parameter.order;

import com.happycommunity.business.model.parameter.CommonBusinessParameter;
import com.happycommunity.framework.common.model.dto.goods.GoodsDTO;
import com.happycommunity.framework.common.model.dto.order.OrderDetailDTO;
import com.happycommunity.framework.common.model.dto.user.UserDTO;
import com.happycommunity.framework.common.model.parameter.BaseParameter;

import java.util.List;

/**
 * @author Danny
 * @Title: CreateOrderParameter
 * @Description:
 * @Created on 2019-01-09 22:56:46
 */
public class CreateOrderParameter extends CommonBusinessParameter {

    private UserDTO userDTO;

    private List<OrderDetailDTO> orderDetailDTOList;

    public UserDTO getUserDTO() {
        return userDTO;
    }

    public CreateOrderParameter setUserDTO(UserDTO userDTO) {
        this.userDTO = userDTO;
        return this;
    }

    public List<OrderDetailDTO> getOrderDetailDTOList() {
        return orderDetailDTOList;
    }

    public CreateOrderParameter setOrderDetailDTOList(List<OrderDetailDTO> orderDetailDTOList) {
        this.orderDetailDTOList = orderDetailDTOList;
        return this;
    }
}
