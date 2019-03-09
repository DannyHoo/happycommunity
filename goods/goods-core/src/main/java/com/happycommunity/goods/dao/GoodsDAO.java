package com.happycommunity.goods.dao;

import com.happycommunity.goods.domain.GoodsDO;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.StatementType;

/**
 * @author Danny
 * @Title: GoodsDAO
 * @Description:
 * @Created on 2019-01-09 22:34:00
 */
@Mapper
public interface GoodsDAO {

    @Select("select * from t_goods where goodsNo=#{goodsNo}")
    GoodsDO findByGoodsNo(@Param("goodsNo")String goodsNo);

    @Insert("insert into t_goods(goodsName,originPrice,nowPrice,totalNum,balance,description,pictureUrls,status) values (#{goodsName},#{originPrice},#{nowPrice},#{totalNum},#{balance},#{description},#{pictureUrls},#{status}")
    @SelectKey(before = false, keyProperty = "id", resultType = Long.class, statementType = StatementType.STATEMENT, statement = "SELECT LAST_INSERT_ID() AS id")
    int saveGoods(GoodsDO goodsDO);

    @Update("update t_goods set goodsName=#{goodsName},originPrice=#{originPrice},nowPrice=#{nowPrice},totalNum=#{totalNum},balance=#{balance},description=#{description},pictureUrls=#{pictureUrls},status=#{status} where id=#{id}")
    int update(GoodsDO goodsDO);
}
