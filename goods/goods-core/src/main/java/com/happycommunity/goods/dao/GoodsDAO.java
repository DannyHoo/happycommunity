package com.happycommunity.goods.dao;

import com.happycommunity.goods.domain.GoodsDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * @author Danny
 * @Title: GoodsDAO
 * @Description:
 * @Created on 2019-01-09 22:34:00
 */
@Mapper
public interface GoodsDAO {

    @Select("select * from t_goods where goodsNo=#{goodsNo}")
    GoodsDO findByGoodsNo(String goodsNo);

}
