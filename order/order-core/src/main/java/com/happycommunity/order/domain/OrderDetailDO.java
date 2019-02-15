package com.happycommunity.order.domain;

import com.happycommunity.framework.common.model.domain.BaseDO;

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
    private String goodsNum;
    /* 订单总金额 */
    private String totalPrice;
    /* 应付价格 */
    private String actualPrice;

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

    public String getGoodsNum() {
        return goodsNum;
    }

    public OrderDetailDO setGoodsNum(String goodsNum) {
        this.goodsNum = goodsNum;
        return this;
    }

    public String getTotalPrice() {
        return totalPrice;
    }

    public OrderDetailDO setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
        return this;
    }

    public String getActualPrice() {
        return actualPrice;
    }

    public OrderDetailDO setActualPrice(String actualPrice) {
        this.actualPrice = actualPrice;
        return this;
    }
}
