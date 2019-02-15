package com.happycommunity.business.service.impl.goods;

import com.alibaba.dubbo.config.annotation.Reference;
import com.happycommunity.business.service.goods.GoodsBusinessService;
import com.happycommunity.goods.service.GoodsService;
import org.springframework.stereotype.Service;

/**
 * @author Danny
 * @Title: GoodsBusinessServiceImpl
 * @Description:
 * @Created on 2018-12-19 15:01:45
 */
@Service("goodsBusinessService")
@com.alibaba.dubbo.config.annotation.Service(version = "1.0.0", interfaceClass = GoodsBusinessService.class,filter = "dubboContextFilter")
public class GoodsBusinessServiceImpl implements GoodsBusinessService {

    @Reference(version = "1.0.0")
    private GoodsService goodsService;

}
