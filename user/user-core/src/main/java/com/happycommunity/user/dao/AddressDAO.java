package com.happycommunity.user.dao;

import com.happycommunity.user.domain.AddressDO;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.StatementType;

import java.util.List;

/**
 * @author Danny
 * @Title: AddressDAO
 * @Description:
 * @Created on 2018-11-27 17:56:36
 */
@Mapper
public interface AddressDAO {
    @Select("select * from t_address where userName=#{userName}")
    List<AddressDO> findByUserName(String userName);

    @Select("select * from t_address where userName=#{userName} and isDefault=#{isDefault}")
    List<AddressDO> findByUserNameAndIsDefault(@Param("userName") String userName, @Param("isDefault") Integer isDefault);

    @Insert("insert into t_address(userName,receiverName,receiverMobileNo,receiverAddress,isDefault) values (#{userName},#{receiverName},#{receiverMobileNo},#{receiverAddress},#{isDefault})")
    @SelectKey(before = false, keyProperty = "id", resultType = Long.class, statementType = StatementType.STATEMENT, statement = "SELECT LAST_INSERT_ID() AS id")
    int saveAddress(AddressDO addressDO);

}
