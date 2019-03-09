package com.happycommunity.goods.dao;

import com.alibaba.fastjson.JSON;
import com.happycommunity.goods.domain.GoodsDO;
import com.happycommunity.goods.AbstractTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Danny
 * @Title: GoodsDaoTest
 * @Description:
 * @Created on 2019-02-15 23:00:35
 */
public class GoodsDaoTest extends AbstractTest {

    @Autowired
    private GoodsDAO goodsDAO;

    @Test
    public void findGoodsTest(){
        GoodsDO goodsDO=goodsDAO.findByGoodsNo("G20180614160305682062");
        System.out.println(JSON.toJSONString(goodsDO));
    }

    @Test
    public void updateGoodsTest(){
        GoodsDO goodsDO=goodsDAO.findByGoodsNo("G20180614160305682062");
        int count=goodsDAO.update(goodsDO.setBalance(goodsDO.getBalance()-1));
        System.out.println(count);
    }
}
