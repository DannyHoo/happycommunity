package com.happycommunity.framework.common.model.dto.order;

import com.happycommunity.framework.common.model.dto.BaseDTO;

import java.math.BigDecimal;

/**
 * @author Danny
 * @Title: OrderDetailDTO
 * @Description:
 * @Created on 2019-02-25 17:39:08
 */
public class OrderDetailDTO extends BaseDTO {

    /* 订单号 */
    private String orderNo;
    /* 商品编号 */
    private String goodsNo;
    /* 商品数量 */
    private int goodsNum;
    /* 订单总金额 */
    private BigDecimal totalPrice;
    /* 应付价格 */
    private BigDecimal actualPrice;

    public String getOrderNo() {
        return orderNo;
    }

    public OrderDetailDTO setOrderNo(String orderNo) {
        this.orderNo = orderNo;
        return this;
    }

    public String getGoodsNo() {
        return goodsNo;
    }

    public OrderDetailDTO setGoodsNo(String goodsNo) {
        this.goodsNo = goodsNo;
        return this;
    }

    public int getGoodsNum() {
        return goodsNum;
    }

    public OrderDetailDTO setGoodsNum(int goodsNum) {
        this.goodsNum = goodsNum;
        return this;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public OrderDetailDTO setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
        return this;
    }

    public BigDecimal getActualPrice() {
        return actualPrice;
    }

    public OrderDetailDTO setActualPrice(BigDecimal actualPrice) {
        this.actualPrice = actualPrice;
        return this;
    }
}
