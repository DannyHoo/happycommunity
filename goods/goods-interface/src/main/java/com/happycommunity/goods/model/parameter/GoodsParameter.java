package com.happycommunity.goods.model.parameter;

import com.happycommunity.framework.common.model.parameter.BaseParameter;

import java.math.BigDecimal;

/**
 * @author Danny
 * @Title: GoodsParameter
 * @Description:
 * @Created on 2019-02-26 11:26:20
 */
public class GoodsParameter extends BaseParameter {
    /* 商品编号 */
    private String goodsNo;
    /* 商品名称 */
    private String goodsName;
    /* 原价 */
    private BigDecimal originPrice;
    /* 现价 */
    private BigDecimal nowPrice;
    /* 商品总数量 */
    private int totalNum;
    /* 剩余数量 */
    private int balance;
    /*  */
    private String description;
    /*  */
    private String pictureUrls;
    /* 商品状态 10正常 20下架 */
    private Integer status;

    public String getGoodsNo() {
        return goodsNo;
    }

    public GoodsParameter setGoodsNo(String goodsNo) {
        this.goodsNo = goodsNo;
        return this;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public GoodsParameter setGoodsName(String goodsName) {
        this.goodsName = goodsName;
        return this;
    }

    public BigDecimal getOriginPrice() {
        return originPrice;
    }

    public GoodsParameter setOriginPrice(BigDecimal originPrice) {
        this.originPrice = originPrice;
        return this;
    }

    public BigDecimal getNowPrice() {
        return nowPrice;
    }

    public GoodsParameter setNowPrice(BigDecimal nowPrice) {
        this.nowPrice = nowPrice;
        return this;
    }

    public int getTotalNum() {
        return totalNum;
    }

    public GoodsParameter setTotalNum(int totalNum) {
        this.totalNum = totalNum;
        return this;
    }

    public int getBalance() {
        return balance;
    }

    public GoodsParameter setBalance(int balance) {
        this.balance = balance;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public GoodsParameter setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getPictureUrls() {
        return pictureUrls;
    }

    public GoodsParameter setPictureUrls(String pictureUrls) {
        this.pictureUrls = pictureUrls;
        return this;
    }

    public Integer getStatus() {
        return status;
    }

    public GoodsParameter setStatus(Integer status) {
        this.status = status;
        return this;
    }
}
