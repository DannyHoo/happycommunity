package com.happycommunity.business.service.impl.order;

import com.alibaba.dubbo.config.annotation.Reference;
import com.happycommunity.business.model.parameter.order.CreateOrderParameter;
import com.happycommunity.business.model.result.order.CreateOrderResult;
import com.happycommunity.business.service.order.OrderBusinessService;
import com.happycommunity.framework.common.model.dto.goods.GoodsDTO;
import com.happycommunity.framework.common.model.dto.order.OrderDTO;
import com.happycommunity.framework.common.model.dto.order.OrderDetailDTO;
import com.happycommunity.framework.common.model.dto.user.AddressDTO;
import com.happycommunity.framework.common.model.dto.user.UserDTO;
import com.happycommunity.framework.common.model.enums.ResultStatusEnum;
import com.happycommunity.framework.common.model.enums.YesNoEnum;
import com.happycommunity.framework.common.model.result.ServiceResult;
import com.happycommunity.framework.core.util.BeanUtil;
import com.happycommunity.framework.core.util.ListUtil;
import com.happycommunity.framework.core.util.StringUtil;
import com.happycommunity.goods.model.parameter.GoodsParameter;
import com.happycommunity.goods.service.GoodsService;
import com.happycommunity.order.parameter.OrderDetailListParameter;
import com.happycommunity.order.parameter.OrderParameter;
import com.happycommunity.order.service.OrderDetailService;
import com.happycommunity.order.service.OrderService;
import com.happycommunity.user.model.parameter.AddressParameter;
import com.happycommunity.user.service.AddressService;
import com.happycommunity.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author Danny
 * @Title: OrderBusinessServiceImpl
 * @Description:
 * @Created on 2018-12-21 16:01:41
 */
@Service("orderBusinessService")
@com.alibaba.dubbo.config.annotation.Service(version = "1.0.0", interfaceClass = OrderBusinessService.class, filter = "dubboContextFilter")
public class OrderBusinessServiceImpl implements OrderBusinessService {

    @Reference(version = "1.0.0")
    private UserService userService;

    @Reference(version = "1.0.0")
    private AddressService addressService;

    @Reference(version = "1.0.0")
    private GoodsService goodsService;

    @Reference(version = "1.0.0")
    private OrderService orderService;

    @Reference(version = "1.0.0")
    private OrderDetailService orderDetailService;

    @Override
    public synchronized ServiceResult<CreateOrderResult> createOrder(CreateOrderParameter createOrderParameter) {

        //已有数据准备
        UserDTO userDTO = createOrderParameter.getUserDTO();
        List<OrderDetailDTO> orderDetailDTOList = createOrderParameter.getOrderDetailDTOList();

        /* 查询收货人信息 */
        AddressDTO addressDTO;
        ServiceResult<List<AddressDTO>> addressDTOServiceResult = addressService.findByUserNameAndIsDefault(new AddressParameter().setUserName(userDTO.getUserName()).setIsDefault(YesNoEnum.YES.getCode()));
        if (addressDTOServiceResult.isFail() || ListUtil.isEmpty(addressDTOServiceResult.getData())) {
            addressDTOServiceResult = addressService.findByUserName(new AddressParameter().setUserName(userDTO.getUserName()));
            if (addressDTOServiceResult.isFail() || ListUtil.isEmpty(addressDTOServiceResult.getData())) {
                return new ServiceResult<>(ResultStatusEnum.USER_ADDRESS_NOT_EXIST);
            }
        }
        addressDTO = addressDTOServiceResult.getData().get(0);

        /* 远程调用 更新商品库存 */
        String orderNo = "OD" + StringUtil.getRandomTimeStr();
        initOrderDetailDTOList(orderDetailDTOList, orderNo);
        for (OrderDetailDTO orderDetailDTO : orderDetailDTOList) {
            ServiceResult<GoodsDTO> goodsDTOServiceResult = goodsService.findByGoodsNo(new GoodsParameter().setGoodsNo(orderDetailDTO.getGoodsNo()));
            if (goodsDTOServiceResult.isFail() || goodsDTOServiceResult.getData() == null) {
                return new ServiceResult<>(ResultStatusEnum.GOODS_NOT_EXIST);
            }
            GoodsDTO goodsDTO = goodsDTOServiceResult.getData();
            if (goodsDTO.getBalance() <orderDetailDTO.getGoodsNum()) {
                return new ServiceResult<>(ResultStatusEnum.GOODS_BALANCE_NOT_ENOUGH);
            }
            orderDetailDTO.setActualPrice(orderDetailDTO.getActualPrice().add(new BigDecimal(orderDetailDTO.getGoodsNum()).multiply(goodsDTO.getNowPrice())));
            orderDetailDTO.setTotalPrice(orderDetailDTO.getTotalPrice().add(new BigDecimal(orderDetailDTO.getGoodsNum()).multiply(goodsDTO.getOriginPrice())));
            //远程调用更新库存
            ServiceResult<Boolean> saveGoodsResult = goodsService.updateGoods(BeanUtil.convertIgnoreNullProperty(goodsDTO, GoodsParameter.class).setBalance(goodsDTO.getBalance() - orderDetailDTO.getGoodsNum()));
            if (saveGoodsResult.isFail() || !Boolean.TRUE.equals(saveGoodsResult.getData())) {
                return new ServiceResult<>(ResultStatusEnum.GOODS_BALANCE_UPDATE_FAILURE);
            }
        }

        /* 计算价格 */
        BigDecimal totalPrice, cutDownPrice, freight, actualPrice;
        totalPrice = calcTotalPrice(orderDetailDTOList);
        cutDownPrice = BigDecimal.ZERO;
        freight = BigDecimal.ZERO;
        actualPrice = totalPrice.subtract(cutDownPrice).add(freight);

        /* 组装订单信息*/
        OrderParameter orderParameter = new OrderParameter()
                .setOrderNo(orderNo)
                .setUserName(userDTO.getUserName())
                .setReceiverName(addressDTO.getReceiverName())
                .setReceiverMobileNo(addressDTO.getReceiverMobileNo())
                .setReceiverAddress(addressDTO.getReceiverAddress())
                .setPayType(10)
                .setStatus(10)
                .setDeliverType(10)
                .setDeliverTime(10)
                .setTotalPrice(totalPrice)
                .setCutDownPrice(BigDecimal.ZERO)
                .setFreight(freight)
                .setActualPrice(BigDecimal.ZERO);

        /* 远程调用 订单入库 */
        ServiceResult<OrderDTO> saveOrderResult = orderService.saveOrder(orderParameter);
        if (saveOrderResult.isFail() || saveOrderResult.getData() == null) {
            // TODO: 2019-02-26
        }
        /* 远程调用 订单详情入库 */
        ServiceResult<List<OrderDetailDTO>> saveOrderDetailListResult = orderDetailService.saveOrderDetailList(new OrderDetailListParameter().setOrderDetailDTOList(orderDetailDTOList));
        if (saveOrderDetailListResult.isFail() || ListUtil.isEmpty(saveOrderDetailListResult.getData())) {
            // TODO: 2019-02-26
        }
        return new ServiceResult<CreateOrderResult>(ResultStatusEnum.SUCCESS, new CreateOrderResult().setOrderDTO(saveOrderResult.getData()).setOrderDetailDTOList(saveOrderDetailListResult.getData()));
    }

    private void initOrderDetailDTOList(List<OrderDetailDTO> orderDetailDTOList, String orderNo) {
        for (OrderDetailDTO orderDetailDTO : orderDetailDTOList) {
            orderDetailDTO.setTotalPrice(BigDecimal.ZERO)
                    .setActualPrice(BigDecimal.ZERO)
                    .setOrderNo(orderNo);
        }
    }

    private BigDecimal calcTotalPrice(List<OrderDetailDTO> orderDetailDTOList) {
        BigDecimal totalPrice = BigDecimal.ZERO;
        if (ListUtil.isNotEmpty(orderDetailDTOList)) {
            for (OrderDetailDTO orderDetailDTO : orderDetailDTOList) {
                totalPrice = totalPrice.add(orderDetailDTO.getActualPrice());
            }
        }
        return totalPrice;
    }

}
