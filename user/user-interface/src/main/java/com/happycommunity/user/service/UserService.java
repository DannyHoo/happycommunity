package com.happycommunity.user.service;

import com.happycommunity.framework.common.model.dto.user.UserDTO;
import com.happycommunity.framework.common.model.result.ServiceResult;
import com.happycommunity.user.model.parameter.UserParameter;

/**
 * @author Danny
 * @Title: UserService
 * @Description:
 * @Created on 2018-11-26 15:16:00
 */
public interface UserService {

    public ServiceResult<UserDTO> findByUserName(UserParameter userParameter);

    public ServiceResult<UserDTO> saveUser(UserParameter userParameter);

}
