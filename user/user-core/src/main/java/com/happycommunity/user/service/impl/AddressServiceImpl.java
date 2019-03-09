package com.happycommunity.user.service.impl;

import com.happycommunity.framework.common.model.dto.user.AddressDTO;
import com.happycommunity.framework.common.model.dto.user.UserDTO;
import com.happycommunity.framework.common.model.enums.ResultStatusEnum;
import com.happycommunity.framework.common.model.result.ServiceResult;
import com.happycommunity.framework.core.util.BeanUtil;
import com.happycommunity.user.dao.AddressDAO;
import com.happycommunity.user.domain.AddressDO;
import com.happycommunity.user.model.parameter.AddressParameter;
import com.happycommunity.user.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Danny
 * @Title: AddressServiceImpl
 * @Description:
 * @Created on 2019-02-25 18:28:39
 */
@Service("addressService")
@com.alibaba.dubbo.config.annotation.Service(version="1.0.0",interfaceClass =AddressService.class,filter = "dubboContextFilter")
public class AddressServiceImpl implements AddressService {

    @Autowired
    private AddressDAO addressDAO;


    @Override
    public ServiceResult<List<AddressDTO>> findByUserName(AddressParameter addressParameter) {
        List<AddressDO> addressDOList=addressDAO.findByUserName(addressParameter.getUserName());
        List<AddressDTO> addressDTOList= BeanUtil.convertList(addressDOList,AddressDTO.class);
        return new ServiceResult<List<AddressDTO>>(ResultStatusEnum.SUCCESS, addressDTOList);
    }

    @Override
    public ServiceResult<List<AddressDTO>> findByUserNameAndIsDefault(AddressParameter addressParameter) {
        List<AddressDO> addressDOList=addressDAO.findByUserNameAndIsDefault(addressParameter.getUserName(),addressParameter.getIsDefault());
        List<AddressDTO> addressDTOList= BeanUtil.convertList(addressDOList,AddressDTO.class);
        return new ServiceResult<List<AddressDTO>>(ResultStatusEnum.SUCCESS, addressDTOList);

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ServiceResult<AddressDTO> saveAddress(AddressParameter addressParameter) {
        AddressDO addressDO=BeanUtil.convertIgnoreNullProperty(addressParameter,AddressDO.class);
        int counts =addressDAO.saveAddress(addressDO);
        if (counts==1){
            AddressDTO addressDTO=BeanUtil.convertIgnoreNullProperty(addressDO,AddressDTO.class);
            return new ServiceResult<AddressDTO>(ResultStatusEnum.SUCCESS,addressDTO);
        }else{
            return new ServiceResult<>(ResultStatusEnum.FAILURE);
        }
    }


}
