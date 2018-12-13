package com.happycommunity.business.service.user;

import com.happycommunity.business.model.parameter.LoginParameter;
import com.happycommunity.business.model.parameter.RegisterParameter;
import com.happycommunity.business.model.result.LoginResult;
import com.happycommunity.business.model.result.RegisterResult;
import com.happycommunity.framework.common.model.result.ServiceResult;

/**
 * @author Danny
 * @Title: UserBusinessService
 * @Description:
 * @Created on 2018-11-26 15:41:47
 */
public interface UserBusinessService {

    public ServiceResult<RegisterResult> register(RegisterParameter registerParameter);

    public ServiceResult<LoginResult> login(LoginParameter loginParameter);


}
