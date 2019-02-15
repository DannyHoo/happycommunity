package com.happycommunity.gateway.controller.app.user;

import com.happycommunity.business.model.parameter.user.LoginParameter;
import com.happycommunity.business.model.parameter.user.RegisterParameter;

import com.happycommunity.business.model.result.user.LoginResult;
import com.happycommunity.business.model.result.user.RegisterResult;
import com.happycommunity.business.service.user.UserBusinessService;
import com.happycommunity.framework.common.model.result.ServiceResult;
import com.happycommunity.framework.core.util.BeanUtil;
import com.happycommunity.gateway.request.app.user.LoginRequest;
import com.happycommunity.gateway.request.app.user.RegisterRequest;
import com.happycommunity.gateway.response.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Danny
 * @Title: UserController
 * @Description:
 * @Created on 2018-11-26 22:36:49
 */
@Controller
@RequestMapping("/app/user")
public class UserController {

    @Autowired
    private UserBusinessService userBusinessService;

    @RequestMapping("/register")
    @ResponseBody
    public ResponseData register(HttpServletRequest request, @RequestBody RegisterRequest registerRequest) {
        RegisterParameter registerParameter = BeanUtil.convertIgnoreNullProperty(registerRequest, RegisterParameter.class);
        ServiceResult<RegisterResult> registerResult = userBusinessService.register(registerParameter);
        return new ResponseData(registerResult);
    }

    @RequestMapping("/login")
    @ResponseBody
    public ResponseData login(HttpServletRequest request, @RequestBody LoginRequest loginRequest) {
        LoginParameter loginParameter = BeanUtil.convertIgnoreNullProperty(loginRequest, LoginParameter.class);
        ServiceResult<LoginResult> loginResult = userBusinessService.login(loginParameter);
        return new ResponseData(loginResult);
    }
}
