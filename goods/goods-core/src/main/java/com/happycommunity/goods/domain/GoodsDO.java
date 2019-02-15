package com.happycommunity.goods.domain;

import com.happycommunity.framework.common.model.domain.BaseDO;

/**
 * @author Danny
 * @Title: GoodsDO
 * @Description:
 * @Created on 2019-01-09 22:34:17
 */
public class GoodsDO extends BaseDO{
    /* 商品编号 */
    private String goodsNo;
    /* 商品名称 */
    private String goodsName;
    /* 原价 */
    private String originPrice;
    /* 现价 */
    private String nowPrice;
    /* 商品总数量 */
    private String totalNum;
    /* 剩余数量 */
    private String balance;
    /*  */
    private String description;
    /*  */
    private String pictureUrls;

    public String getGoodsNo() {
        return goodsNo;
    }

    public GoodsDO setGoodsNo(String goodsNo) {
        this.goodsNo = goodsNo;
        return this;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public GoodsDO setGoodsName(String goodsName) {
        this.goodsName = goodsName;
        return this;
    }

    public String getOriginPrice() {
        return originPrice;
    }

    public GoodsDO setOriginPrice(String originPrice) {
        this.originPrice = originPrice;
        return this;
    }

    public String getNowPrice() {
        return nowPrice;
    }

    public GoodsDO setNowPrice(String nowPrice) {
        this.nowPrice = nowPrice;
        return this;
    }

    public String getTotalNum() {
        return totalNum;
    }

    public GoodsDO setTotalNum(String totalNum) {
        this.totalNum = totalNum;
        return this;
    }

    public String getBalance() {
        return balance;
    }

    public GoodsDO setBalance(String balance) {
        this.balance = balance;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public GoodsDO setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getPictureUrls() {
        return pictureUrls;
    }

    public GoodsDO setPictureUrls(String pictureUrls) {
        this.pictureUrls = pictureUrls;
        return this;
    }
}
