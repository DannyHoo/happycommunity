package com.happycommunity.user.service.impl;

import com.alibaba.fastjson.JSON;
import com.happycommunity.framework.common.model.dto.user.UserDTO;
import com.happycommunity.framework.common.model.result.ServiceResult;
import com.happycommunity.framework.core.util.BeanUtil;
import com.happycommunity.user.AbstractTest;
import com.happycommunity.user.domain.UserDO;
import com.happycommunity.user.model.parameter.UserListParameter;
import com.happycommunity.user.model.parameter.UserParameter;
import com.happycommunity.user.service.UserService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Danny
 * @Title: UserServiceImplTest
 * @Description:
 * @Created on 2018-11-27 15:14:43
 */
public class UserServiceImplTest extends AbstractTest {

    @Autowired
    private UserService userService;

    @Test
    public void findByUserNameTest(){
        ServiceResult<UserDTO> userDTO=userService.findByUserName(new com.happycommunity.user.model.parameter.UserParameter().setUserName("Danny"));
        System.out.println(JSON.toJSONString(userDTO));
    }

    @Test
    public void saveUserTest(){
        UserDTO userDTO=new UserDTO().setUserName("Song").setMobileNo("13094805984").setSalt("XREybXwZ").setPassword("123456").setEmail("123@qq.com").setRealName("丹尼胡").setIdCardNo("130732199202103227");
        ServiceResult<UserDTO> result=userService.saveUser(BeanUtil.convertIgnoreNullProperty(userDTO, UserParameter.class));
        System.out.println(JSON.toJSONString(result));
    }

    @Test
    public void saveUserListTest() throws Exception {
        List<UserDTO> userDOList=new ArrayList<UserDTO>();
        userDOList.add(new UserDTO().setUserName("Song1").setMobileNo("13094805984").setSalt("XREybXwZ").setPassword("123456").setEmail("123@qq.com").setRealName("丹尼胡").setIdCardNo("130732199202103227"));
        userDOList.add(new UserDTO().setUserName("Song2").setMobileNo("13094805984").setSalt("XREybXwZ").setPassword("123456").setEmail("123@qq.com").setRealName("丹尼胡").setIdCardNo("130732199202103227"));
        ServiceResult<Boolean> saveUserListResult=userService.saveUserList(new UserListParameter().setUserDTOList(userDOList));
        System.out.println(JSON.toJSONString(saveUserListResult));
    }

}
