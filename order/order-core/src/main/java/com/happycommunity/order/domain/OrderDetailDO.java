package com.happycommunity.order.domain;

import com.happycommunity.framework.common.model.domain.BaseDO;

import java.math.BigDecimal;

/**
 * @author Danny
 * @Title: OrderDetailDO
 * @Description:
 * @Created on 2019-01-04 09:49:10
 */
public class OrderDetailDO extends BaseDO {
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

    public OrderDetailDO setOrderNo(String orderNo) {
        this.orderNo = orderNo;
        return this;
    }

    public String getGoodsNo() {
        return goodsNo;
    }

    public OrderDetailDO setGoodsNo(String goodsNo) {
        this.goodsNo = goodsNo;
        return this;
    }

    public int getGoodsNum() {
        return goodsNum;
    }

    public OrderDetailDO setGoodsNum(int goodsNum) {
        this.goodsNum = goodsNum;
        return this;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public OrderDetailDO setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
        return this;
    }

    public BigDecimal getActualPrice() {
        return actualPrice;
    }

    public OrderDetailDO setActualPrice(BigDecimal actualPrice) {
        this.actualPrice = actualPrice;
        return this;
    }
}
