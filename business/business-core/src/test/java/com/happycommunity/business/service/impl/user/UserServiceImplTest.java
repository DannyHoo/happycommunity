package com.happycommunity.business.service.impl.user;

import com.alibaba.fastjson.JSON;
import com.happycommunity.business.AbstractTest;
import com.happycommunity.business.model.parameter.LoginParameter;
import com.happycommunity.business.model.parameter.RegisterParameter;
import com.happycommunity.business.model.result.user.LoginResult;
import com.happycommunity.business.model.result.user.RegisterResult;
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
    public void loginTest() {
        ServiceResult<LoginResult> loginResult = userBusinessService.login(new LoginParameter().setUserName("Danny")
                .setPassword(""));
        System.out.println(JSON.toJSONString(loginResult));
    }

    @Test
    public void registerTest() {
        ServiceResult<RegisterResult> loginResult = userBusinessService.register(new RegisterParameter().setUserName("Danny2")
                .setPassword("L76vt98XUr9vlos4/Q3etKdqDZn6fKdVJMIlYjWgie/s/OQ9QQvjNRQEbyd8ugkdYr4iKM0PycAvrkK2K2/zZBsdko2gCj0XrAKg9Hm2ozD6X9Y676TyxpvftQTQH/YTKYQMrEzCRNje8uib5+5OxA4bH7cWOdLp+vyyeDMkzi4="));
        System.out.println(JSON.toJSONString(loginResult));
    }

}
