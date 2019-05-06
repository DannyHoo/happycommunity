package com.happycommunity.order.dao;

import com.happycommunity.order.domain.OrderDO;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.StatementType;

import java.util.List;

/**
 * @author Danny
 * @Title: OrderDAO
 * @Description:
 * @Created on 2019-01-04 09:57:21
 */
@Mapper
public interface OrderDAO {
    @Select("select * from t_order")
    List<OrderDO> findAll();

    @Select("select * from t_order where orderNo=#{orderNo}")
    OrderDO findByOrderNo(@Param("orderNo") String orderNo);

    @Insert("insert into t_order(orderNo,userName,receiverName,receiverMobileNo,receiverAddress,payType,status,deliverType,deliverTime,totalPrice,cutDownPrice,freight,actualPrice) values (#{orderNo},#{userName},#{receiverName},#{receiverMobileNo},#{receiverAddress},#{payType},#{status},#{deliverType},#{deliverTime},#{totalPrice},#{cutDownPrice},#{freight},#{actualPrice})")
    @SelectKey(before = false, keyProperty = "id", resultType = Long.class, statementType = StatementType.STATEMENT, statement = "SELECT LAST_INSERT_ID() AS id")
    int insertOrderDO(OrderDO orderDO);

    @Insert({
            "<script>"
                    + "insert into t_order(orderNo,userName,receiverName,receiverMobileNo,receiverAddress,payType,status,deliverType,deliverTime,totalPrice,cutDownPrice,freight,actualPrice) "
                    + "values "
                    + "<foreach item='order' index='index' collection='orderDOList' separator=','>"
                    + "(#{order.orderNo},#{order.userName},#{order.receiverName},#{order.receiverMobileNo},#{order.receiverAddress},#{order.payType},#{order.status},#{order.deliverType},#{order.deliverTime},#{order.totalPrice},#{order.cutDownPrice},#{order.freight},#{order.actualPrice})"
                    + "</foreach>"
                    + "</script>"
    })
    @SelectKey(before = false, keyProperty = "id", resultType = Long.class, statementType = StatementType.STATEMENT, statement = "SELECT LAST_INSERT_ID() AS id")
    int insertOrderDOBatch(@Param("orderDOList") List<OrderDO> orderDOList);

}
