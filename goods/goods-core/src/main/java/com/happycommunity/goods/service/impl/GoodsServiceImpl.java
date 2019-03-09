package com.happycommunity.goods.service.impl;

import com.happycommunity.framework.common.model.dto.goods.GoodsDTO;
import com.happycommunity.framework.common.model.enums.ResultStatusEnum;
import com.happycommunity.framework.common.model.result.ServiceResult;
import com.happycommunity.framework.core.util.BeanUtil;
import com.happycommunity.goods.dao.GoodsDAO;
import com.happycommunity.goods.domain.GoodsDO;
import com.happycommunity.goods.model.parameter.GoodsParameter;
import com.happycommunity.goods.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Danny
 * @Title: GoodsServiceImpl
 * @Description:
 * @Created on 2018-12-19 14:09:20
 */
@Service("goodsService")
@com.alibaba.dubbo.config.annotation.Service(version = "1.0.0",interfaceClass = GoodsService.class,filter = "dubboContextFilter")
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    private GoodsDAO goodsDAO;

    @Override
    public ServiceResult<GoodsDTO> findByGoodsNo(GoodsParameter goodsParameter) {
        GoodsDO goodsDO=goodsDAO.findByGoodsNo(goodsParameter.getGoodsNo());
        GoodsDTO goodsDTO= BeanUtil.convertIgnoreNullProperty(goodsDO,GoodsDTO.class);
        if (goodsDTO!=null){
            return new ServiceResult<GoodsDTO>(ResultStatusEnum.SUCCESS,goodsDTO);
        }
        return new ServiceResult<GoodsDTO>(ResultStatusEnum.GOODS_NOT_EXIST);
    }

    @Override
    public ServiceResult<Boolean> saveGoods(GoodsParameter goodsParameter) {
        GoodsDO goodsDO=BeanUtil.convertIgnoreNullProperty(goodsParameter,GoodsDO.class);
        int count=goodsDAO.saveGoods(goodsDO);
        if (count<=0){
            return new ServiceResult<>(ResultStatusEnum.FAILURE);
        }
        return new ServiceResult<>(ResultStatusEnum.SUCCESS,Boolean.TRUE);
    }

    @Override
    public ServiceResult<Boolean> updateGoods(GoodsParameter goodsParameter) {
        GoodsDO goodsDO=BeanUtil.convertIgnoreNullProperty(goodsParameter,GoodsDO.class);
        int count=goodsDAO.update(goodsDO);
        if (count<=0){
            return new ServiceResult<>(ResultStatusEnum.FAILURE);
        }
        return new ServiceResult<>(ResultStatusEnum.SUCCESS,Boolean.TRUE);
    }
}
