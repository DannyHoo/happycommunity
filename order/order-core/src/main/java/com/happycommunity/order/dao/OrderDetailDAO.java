package com.happycommunity.order.dao;

import com.happycommunity.order.domain.OrderDO;
import com.happycommunity.order.domain.OrderDetailDO;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.StatementType;

import java.util.List;

/**
 * @author Danny
 * @Title: OrderDetailDAO
 * @Description:
 * @Created on 2019-02-26 16:04:13
 */
@Mapper
public interface OrderDetailDAO {


    @Insert("insert into t_order_detail(orderNo,goodsNo,goodsNum,totalPrice,actualPrice,comment) values (#{orderNo},#{goodsNo},#{goodsNum},#{totalPrice},#{actualPrice},#{comment}")
    @SelectKey(before = false, keyProperty = "id", resultType = Long.class, statementType = StatementType.STATEMENT, statement = "SELECT LAST_INSERT_ID() AS id")
    int insertOrderDetailDO(OrderDetailDO orderDetailDO);

    @Insert({
            "<script>"
                    + "insert into t_order_detail(orderNo,goodsNo,goodsNum,totalPrice,actualPrice,comment) "
                    + "values "
                    + "<foreach item='orderDetail' index='index' collection='orderDetailDOList' separator=','>"
                    + "(#{orderDetail.orderNo},#{orderDetail.goodsNo},#{orderDetail.goodsNum},#{orderDetail.totalPrice},#{orderDetail.actualPrice},#{orderDetail.comment})"
                    + "</foreach>"
                    + "</script>"
    })
    @SelectKey(before = false, keyProperty = "id", resultType = Long.class, statementType = StatementType.STATEMENT, statement = "SELECT LAST_INSERT_ID() AS id")
    int insertOrderDetailDOBatch(@Param("orderDetailDOList") List<OrderDetailDO> orderDetailDOList);

}
