package com.happycommunity.business.service.impl.user;

import com.alibaba.fastjson.JSON;
import com.happycommunity.business.AbstractTest;
import com.happycommunity.business.model.parameter.user.LoginParameter;
import com.happycommunity.business.model.parameter.user.RegisterParameter;
import com.happycommunity.business.model.result.user.LoginResult;
import com.happycommunity.business.model.result.user.RegisterResult;
import com.happycommunity.business.service.user.UserBusinessService;
import com.happycommunity.framework.common.model.result.ServiceResult;
import com.happycommunity.framework.core.util.StringUtil;
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
        ServiceResult<LoginResult> loginResult = userBusinessService.login(new LoginParameter().setUserName("KCI85b0y")
                .setPassword("ECFxzhYeI6YrucyEi0CG0gQEvOkGl1fh0MdHwrMpdUvnAQfO0UCfj6G1nxqW5yPxgKW62u7/Sb1yVgePbwuVBE5DyKl8vyBOdBF5I2cbi5v9hnfarOFQzvJAWJ7gcGXEFK9zpNk/tqpK4wBgOLwwmL15g9+1x8M2eDIkX/9Fc+U="));
        System.out.println(JSON.toJSONString(loginResult));
    }

    @Test
    public void registerTest() {
        ServiceResult<RegisterResult> loginResult = userBusinessService.register(new RegisterParameter().setUserName(StringUtil.getStringRandom(8))
                .setPassword("ECFxzhYeI6YrucyEi0CG0gQEvOkGl1fh0MdHwrMpdUvnAQfO0UCfj6G1nxqW5yPxgKW62u7/Sb1yVgePbwuVBE5DyKl8vyBOdBF5I2cbi5v9hnfarOFQzvJAWJ7gcGXEFK9zpNk/tqpK4wBgOLwwmL15g9+1x8M2eDIkX/9Fc+U="));
        System.out.println(JSON.toJSONString(loginResult));
    }

    @Test
    public void registerBatchTest() {
        for(int i=0;i<100;i++){
            ServiceResult<RegisterResult> loginResult = userBusinessService.register(new RegisterParameter().setUserName(StringUtil.getStringRandom(8))
                    .setPassword("ECFxzhYeI6YrucyEi0CG0gQEvOkGl1fh0MdHwrMpdUvnAQfO0UCfj6G1nxqW5yPxgKW62u7/Sb1yVgePbwuVBE5DyKl8vyBOdBF5I2cbi5v9hnfarOFQzvJAWJ7gcGXEFK9zpNk/tqpK4wBgOLwwmL15g9+1x8M2eDIkX/9Fc+U="));
            System.out.println(JSON.toJSONString(loginResult));
        }
    }
}
