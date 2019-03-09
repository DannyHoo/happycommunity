package com.happycommunity.business.service.impl.user;

import com.alibaba.dubbo.config.annotation.Reference;
import com.happycommunity.business.config.SystemConfig;
import com.happycommunity.business.model.parameter.user.LoginParameter;
import com.happycommunity.business.model.parameter.user.RegisterParameter;
import com.happycommunity.business.model.result.user.LoginResult;
import com.happycommunity.business.model.result.user.RegisterResult;
import com.happycommunity.business.service.user.UserBusinessService;
import com.happycommunity.framework.common.model.dto.user.UserDTO;
import com.happycommunity.framework.common.model.enums.ResultStatusEnum;
import com.happycommunity.framework.common.model.result.ServiceResult;
import com.happycommunity.framework.core.util.*;
import com.happycommunity.user.model.parameter.UserParameter;
import com.happycommunity.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author Danny
 * @Title: UserBusinessServiceImpl
 * @Description:
 * @Created on 2018-11-26 15:43:39
 */
@Service("userBusinessService")
@com.alibaba.dubbo.config.annotation.Service(version = "1.0.0", interfaceClass = UserBusinessService.class, filter = "dubboContextFilter")
public class UserBusinessServiceImpl implements UserBusinessService {

    @Reference(version = "1.0.0")
    UserService userService;

    @Autowired
    private SystemConfig systemConfig;

    /**
     * 注册
     *
     * @param registerParameter
     * @return
     */
    @Override
    public ServiceResult<RegisterResult> register(RegisterParameter registerParameter) {
        ServiceResult<UserDTO> userDTOResult = userService.findByUserName(new UserParameter().setUserName(registerParameter.getUserName()));
        if (userDTOResult.isSuccess() && userDTOResult.getData() != null) {
            return new ServiceResult(ResultStatusEnum.USER_ALREADY_EXIST);
        }
        UserDTO userDTO = new UserDTO();
        String salt = StringUtil.getStringRandom(8);//MD5加密盐值
        String decryptPassword = "";
        try {
            decryptPassword = RSAUtil.decrypt(registerParameter.getPassword(), systemConfig.getRsaPrivateKey());
        } catch (Exception e) {
            e.printStackTrace();
            return new ServiceResult(ResultStatusEnum.UNKOWN_SYS_ERROR);
        }
        Map randomUserMap = RandomValueUtil.getAddress();
        String md5Password = MD5Util.md5HexTwoSourceAndSalt(decryptPassword, salt);
        userDTO.setUserName(registerParameter.getUserName())
                .setMobileNo(randomUserMap.get("tel").toString())
                .setEmail(registerParameter.getUserName() + "@163.com")
                .setSalt(salt)
                .setPassword(md5Password)
                .setRealName(randomUserMap.get("name").toString()) // TODO: 2019-02-24
                .setIdCardNo(StringUtil.getRandomNum(18));        // TODO: 2019-02-24
        ServiceResult<UserDTO> userDTOSaveResult = userService.saveUser(BeanUtil.convertIgnoreNullProperty(userDTO, UserParameter.class));
        if (userDTOSaveResult.isSuccess() && userDTOSaveResult.getData() != null) {
            return new ServiceResult(ResultStatusEnum.SUCCESS).setData(userDTOSaveResult.getData());
        }
        return new ServiceResult(ResultStatusEnum.FAILURE).setData(userDTOSaveResult.getData());
    }

    /**
     * 登录
     *
     * @param loginParameter
     * @return
     */
    @Override
    public ServiceResult<LoginResult> login(LoginParameter loginParameter) {
        ServiceResult<UserDTO> userDTOResult = userService.findByUserName(new UserParameter().setUserName(loginParameter.getUserName()));
        if (userDTOResult.isFail() || userDTOResult.getData() == null) {
            return new ServiceResult(ResultStatusEnum.USERNAME_OR_PASSWORD_INVALID);
        }
        UserDTO userDTO = userDTOResult.getData();
        String decryptPassword = "";
        try {
            decryptPassword = RSAUtil.decrypt(loginParameter.getPassword(), systemConfig.getRsaPrivateKey());
        } catch (Exception e) {
            e.printStackTrace();
            return new ServiceResult(ResultStatusEnum.UNKOWN_SYS_ERROR);
        }
        if (MD5Util.md5HexTwoSourceAndSalt(decryptPassword, userDTO.getSalt()).equals(userDTO.getPassword())) {
            return new ServiceResult(ResultStatusEnum.SUCCESS).setData(userDTO);
        }
        return new ServiceResult(ResultStatusEnum.USERNAME_OR_PASSWORD_INVALID);
    }

    @Override
    public ServiceResult<UserDTO> findByUserName(LoginParameter loginParameter) {
        ServiceResult<UserDTO> userDTOResult = userService.findByUserName(new UserParameter().setUserName(loginParameter.getUserName()));
        if (userDTOResult.isFail() || userDTOResult.getData() == null) {
            return new ServiceResult(ResultStatusEnum.USER_NOT_EXIST);
        }
        return new ServiceResult<>(ResultStatusEnum.SUCCESS,userDTOResult.getData());
    }

}
