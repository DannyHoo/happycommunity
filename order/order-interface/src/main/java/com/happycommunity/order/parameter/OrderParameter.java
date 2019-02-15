package com.happycommunity.order.parameter;

import com.happycommunity.framework.common.model.parameter.BaseParameter;

import java.math.BigDecimal;

/**
 * @author Danny
 * @Title: OrderParameter
 * @Description:
 * @Created on 2019-01-09 23:15:07
 */
public class OrderParameter extends CommonOrderParameter {
    /* 订单号 */
    private String orderNo;
    /* 用户名 */
    private String userName;
    /* 收货人 */
    private String receiverName;
    /* 收货人电话 */
    private String receiverMobileNo;
    /* 收货地址 */
    private String receiverAddress;
    /* 支付方式：10在线付款20货到付款30他人代付 */
    private Integer payType;
    /* 订单状态：10待付款，20已付款待发货，30已退货，40已完成 */
    private Integer status;
    /* 配送方式：10快递配送20上门自取 */
    private Integer deliverType;
    /* 送货时间：10不限送货时间20工作日送货30节假日配送 */
    private Integer deliverTime;
    /* 订单总金额 */
    private BigDecimal totalPrice;
    /* 订单优惠金额 */
    private BigDecimal cutDownPrice;
    /* 运费 */
    private BigDecimal freight;
    /* 应付总额 */
    private BigDecimal actualPrice;

    public String getOrderNo() {
        return orderNo;
    }

    public OrderParameter setOrderNo(String orderNo) {
        this.orderNo = orderNo;
        return this;
    }

    public String getUserName() {
        return userName;
    }

    public OrderParameter setUserName(String userName) {
        this.userName = userName;
        return this;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public OrderParameter setReceiverName(String receiverName) {
        this.receiverName = receiverName;
        return this;
    }

    public String getReceiverMobileNo() {
        return receiverMobileNo;
    }

    public OrderParameter setReceiverMobileNo(String receiverMobileNo) {
        this.receiverMobileNo = receiverMobileNo;
        return this;
    }

    public String getReceiverAddress() {
        return receiverAddress;
    }

    public OrderParameter setReceiverAddress(String receiverAddress) {
        this.receiverAddress = receiverAddress;
        return this;
    }

    public Integer getPayType() {
        return payType;
    }

    public OrderParameter setPayType(Integer payType) {
        this.payType = payType;
        return this;
    }

    public Integer getStatus() {
        return status;
    }

    public OrderParameter setStatus(Integer status) {
        this.status = status;
        return this;
    }

    public Integer getDeliverType() {
        return deliverType;
    }

    public OrderParameter setDeliverType(Integer deliverType) {
        this.deliverType = deliverType;
        return this;
    }

    public Integer getDeliverTime() {
        return deliverTime;
    }

    public OrderParameter setDeliverTime(Integer deliverTime) {
        this.deliverTime = deliverTime;
        return this;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public OrderParameter setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
        return this;
    }

    public BigDecimal getCutDownPrice() {
        return cutDownPrice;
    }

    public OrderParameter setCutDownPrice(BigDecimal cutDownPrice) {
        this.cutDownPrice = cutDownPrice;
        return this;
    }

    public BigDecimal getFreight() {
        return freight;
    }

    public OrderParameter setFreight(BigDecimal freight) {
        this.freight = freight;
        return this;
    }

    public BigDecimal getActualPrice() {
        return actualPrice;
    }

    public OrderParameter setActualPrice(BigDecimal actualPrice) {
        this.actualPrice = actualPrice;
        return this;
    }
}
