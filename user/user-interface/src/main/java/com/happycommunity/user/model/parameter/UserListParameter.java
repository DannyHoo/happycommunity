package com.happycommunity.user.model.parameter;

import com.happycommunity.framework.common.model.dto.user.UserDTO;
import com.happycommunity.framework.common.model.model.GlobalTraceData;
import com.happycommunity.framework.common.model.parameter.BaseParameter;

import java.util.List;

/**
 * @author Danny
 * @Title: UserListParameter
 * @Description:
 * @Created on 2018-12-14 15:35:52
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
