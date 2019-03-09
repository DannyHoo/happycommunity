package com.happycommunity.user.service.impl;

import com.alibaba.fastjson.JSON;
import com.happycommunity.framework.common.model.dto.user.AddressDTO;
import com.happycommunity.framework.common.model.result.ServiceResult;
import com.happycommunity.user.AbstractTest;
import com.happycommunity.user.model.parameter.AddressParameter;
import com.happycommunity.user.service.AddressService;
import com.happycommunity.user.service.UserService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author Danny
 * @Title: AddressServiceImplTest
 * @Description:
 * @Created on 2019-02-25 18:37:27
 */
public class AddressServiceImplTest extends AbstractTest {

    @Autowired
    private AddressService addressService;

    @Test
    public void findTest() {

        ServiceResult<List<AddressDTO>> result = addressService.findByUserNameAndIsDefault(
                new AddressParameter().setUserName("Song6").setIsDefault(10));
        System.out.println(JSON.toJSONString(result));
    }

    @Test
    public void saveTest() {
        AddressParameter addressParameter=getAddressParameter();
        ServiceResult<AddressDTO> result = addressService.saveAddress(addressParameter);
        System.out.println(JSON.toJSONString(result));
    }

    private AddressParameter getAddressParameter() {
        return (AddressParameter)new AddressParameter()
                .setUserName("Danny")
                .setReceiverName("李四二")
                .setReceiverMobileNo("13579246810")
                .setReceiverAddress("河北省张家口市育隆大街234号")
                .setIsDefault(20)
                .setComment("河北省张家口市育隆大街234号");
    }

}
