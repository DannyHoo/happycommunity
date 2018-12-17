package com.happycommunity.business.model.parameter.user;

import com.happycommunity.framework.common.model.dto.user.UserDTO;
import com.happycommunity.framework.common.model.model.GlobalTraceData;

import java.util.List;

/**
 * @author Danny
 * @Title: UserListParameter
 * @Description:
 * @Created on 2018-12-17 15:37:08
 */
public class UserListParameter extends GlobalTraceData {
    private List<UserDTO> userDTOList;

    public List<UserDTO> getUserDTOList() {
        return userDTOList;
    }

    public UserListParameter setUserDTOList(List<UserDTO> userDTOList) {
        this.userDTOList = userDTOList;
        return this;
    }

}
