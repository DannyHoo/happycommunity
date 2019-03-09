package com.happycommunity.user.service;

import com.happycommunity.framework.common.model.dto.user.AddressDTO;
import com.happycommunity.framework.common.model.result.ServiceResult;
import com.happycommunity.user.model.parameter.AddressParameter;

import java.util.List;

/**
 * @author Danny
 * @Title: AddressService
 * @Description:
 * @Created on 2019-02-25 18:26:50
 */
public interface AddressService {
    ServiceResult<List<AddressDTO>> findByUserName(AddressParameter addressParameter);

    ServiceResult<List<AddressDTO>> findByUserNameAndIsDefault(AddressParameter addressParameter);

    ServiceResult<AddressDTO> saveAddress(AddressParameter addressParameter);

}
