package com.happycommunity.framework.common.model.dto.goods;

import com.happycommunity.framework.common.model.dto.BaseDTO;

import java.math.BigDecimal;

/**
 * @author Danny
 * @Title: GoodsDTO
 * @Description:
 * @Created on 2019-02-25 17:30:37
 */
public class GoodsDTO extends BaseDTO {
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

    public GoodsDTO setGoodsNo(String goodsNo) {
        this.goodsNo = goodsNo;
        return this;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public GoodsDTO setGoodsName(String goodsName) {
        this.goodsName = goodsName;
        return this;
    }

    public BigDecimal getOriginPrice() {
        return originPrice;
    }

    public GoodsDTO setOriginPrice(BigDecimal originPrice) {
        this.originPrice = originPrice;
        return this;
    }

    public BigDecimal getNowPrice() {
        return nowPrice;
    }

    public GoodsDTO setNowPrice(BigDecimal nowPrice) {
        this.nowPrice = nowPrice;
        return this;
    }

    public int getTotalNum() {
        return totalNum;
    }

    public GoodsDTO setTotalNum(int totalNum) {
        this.totalNum = totalNum;
        return this;
    }

    public int getBalance() {
        return balance;
    }

    public GoodsDTO setBalance(int balance) {
        this.balance = balance;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public GoodsDTO setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getPictureUrls() {
        return pictureUrls;
    }

    public GoodsDTO setPictureUrls(String pictureUrls) {
        this.pictureUrls = pictureUrls;
        return this;
    }

    public Integer getStatus() {
        return status;
    }

    public GoodsDTO setStatus(Integer status) {
        this.status = status;
        return this;
    }
}
