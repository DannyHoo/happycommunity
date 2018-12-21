package com.happycommunity.goods.service;

import com.happycommunity.framework.common.model.result.ServiceResult;

/**
 * @author Danny
 * @Title: GoodsService
 * @Description:
 * @Created on 2018-12-19 14:09:10
 */
public interface GoodsService {

    public ServiceResult<Object> findByUserName();

}
