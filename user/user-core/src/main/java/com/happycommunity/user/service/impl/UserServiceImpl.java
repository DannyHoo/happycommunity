package com.happycommunity.user.service.impl;


import com.happycommunity.framework.common.model.dto.user.UserDTO;
import com.happycommunity.framework.common.model.enums.ResultStatusEnum;
import com.happycommunity.framework.common.model.result.ServiceResult;
import com.happycommunity.framework.core.util.BeanUtil;
import com.happycommunity.user.dao.UserDAO;
import com.happycommunity.user.domain.UserDO;
import com.happycommunity.user.model.parameter.UserListParameter;
import com.happycommunity.user.model.parameter.UserParameter;
import com.happycommunity.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Danny
 * @Title: UserServiceImpl
 * @Description:
 * @Created on 2018-11-26 20:11:32
 */
@Service("userService")
@com.alibaba.dubbo.config.annotation.Service(version = "1.0.0", interfaceClass = UserService.class, filter = "dubboContextFilter")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO userDAO;

    @Override
    public ServiceResult<UserDTO> findByUserName(UserParameter userParameter) {
        UserDO userDO = userDAO.findByUserName(userParameter.getUserName());
        UserDTO userDTO = BeanUtil.convertIgnoreNullProperty(userDO, UserDTO.class);
        return new ServiceResult<UserDTO>(ResultStatusEnum.SUCCESS, userDTO);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ServiceResult<UserDTO> saveUser(UserParameter userParameter) {
        UserDO userDO = BeanUtil.convertIgnoreNullProperty(userParameter, UserDO.class);
        int counts = userDAO.insertUserDO(userDO);
        if (counts == 1 && userDO.getId() != null) {
            UserDTO userDTOFound = BeanUtil.convertIgnoreNullProperty(userDO, UserDTO.class);
            return new ServiceResult<UserDTO>(ResultStatusEnum.SUCCESS, userDTOFound);
        }
        return new ServiceResult<UserDTO>(ResultStatusEnum.FAILURE);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ServiceResult<Boolean> saveUserList(UserListParameter userListParameter) throws Exception {
        List<UserDO> userDOList = BeanUtil.convertList(userListParameter.getUserDTOList(), UserDO.class);
        int result = userDAO.insertUserDOBatch(userDOList);
        if (result == userDOList.size()) {
            return new ServiceResult<Boolean>(ResultStatusEnum.SUCCESS, true);
        }
        return new ServiceResult<Boolean>(ResultStatusEnum.FAILURE);
    }

}
