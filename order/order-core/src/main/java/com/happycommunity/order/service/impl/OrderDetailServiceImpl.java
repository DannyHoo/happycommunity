package com.happycommunity.order.service.impl;

import com.happycommunity.framework.common.model.dto.order.OrderDetailDTO;
import com.happycommunity.framework.common.model.enums.ResultStatusEnum;
import com.happycommunity.framework.common.model.result.ServiceResult;
import com.happycommunity.framework.core.util.BeanUtil;
import com.happycommunity.order.dao.OrderDetailDAO;
import com.happycommunity.order.domain.OrderDetailDO;
import com.happycommunity.order.parameter.OrderDetailListParameter;
import com.happycommunity.order.service.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Danny
 * @Title: OrderDetailServiceImpl
 * @Description:
 * @Created on 2019-02-26 16:40:10
 */
@Service("orderDetailService")
@com.alibaba.dubbo.config.annotation.Service(version = "1.0.0",interfaceClass = OrderDetailService.class,filter = "dubboContextFilter")
public class OrderDetailServiceImpl implements OrderDetailService {

    @Autowired
    private OrderDetailDAO orderDetailDAO;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ServiceResult<List<OrderDetailDTO>> saveOrderDetailList(OrderDetailListParameter orderDetailListParameter) {
        List<OrderDetailDO> orderDetailDOList=BeanUtil.convertList(orderDetailListParameter.getOrderDetailDTOList(),OrderDetailDO.class);
        int result=orderDetailDAO.insertOrderDetailDOBatch(orderDetailDOList);
        if (result==orderDetailDOList.size()){
            return new ServiceResult<>(ResultStatusEnum.SUCCESS,BeanUtil.convertList(orderDetailDOList,OrderDetailDTO.class));
        }
        return new ServiceResult<>(ResultStatusEnum.FAILURE);
    }

}
