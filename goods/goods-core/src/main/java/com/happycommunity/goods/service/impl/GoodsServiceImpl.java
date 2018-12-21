package com.happycommunity.goods.service.impl;

import com.happycommunity.framework.common.model.result.ServiceResult;
import com.happycommunity.goods.service.GoodsService;
import org.springframework.stereotype.Service;

/**
 * @author Danny
 * @Title: GoodsServiceImpl
 * @Description:
 * @Created on 2018-12-19 14:09:20
 */
@Service("goodsService")
public class GoodsServiceImpl implements GoodsService {

    @Override
    public ServiceResult<Object> findByUserName() {
        System.out.println(111);
        return null;
    }
}
