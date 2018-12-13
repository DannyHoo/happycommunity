package com.happycommunity.business.impl;

import com.alibaba.fastjson.JSON;
import com.happycommunity.business.AbstractTest;
import com.happycommunity.business.model.parameter.LoginParameter;
import com.happycommunity.business.model.result.LoginResult;
import com.happycommunity.business.service.user.UserBusinessService;
import com.happycommunity.framework.common.model.result.ServiceResult;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Danny
 * @Title: UserServiceImplTest
 * @Description:
 * @Created on 2018-11-27 15:14:43
 */
public class UserServiceImplTest extends AbstractTest {

    @Autowired
    private UserBusinessService userBusinessService;

    @Test
    public void findByUserNameTest() {
        ServiceResult<LoginResult> loginResult = userBusinessService.login(new LoginParameter().setUserName("Danny")
                .setPassword(""));
        System.out.println(JSON.toJSONString(loginResult));
    }

}
