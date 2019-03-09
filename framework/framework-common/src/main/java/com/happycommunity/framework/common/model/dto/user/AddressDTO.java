package com.happycommunity.framework.common.model.dto.user;

import com.happycommunity.framework.common.model.dto.BaseDTO;

/**
 * @author Danny
 * @Title: AddressDTO
 * @Description:
 * @Created on 2019-02-25 18:33:21
 */
public class AddressDTO extends BaseDTO {
    private String userName;
    private String receiverName;
    private String receiverMobileNo;
    private String receiverAddress;
    private Integer isDefault;

    public String getUserName() {
        return userName;
    }

    public AddressDTO setUserName(String userName) {
        this.userName = userName;
        return this;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public AddressDTO setReceiverName(String receiverName) {
        this.receiverName = receiverName;
        return this;
    }

    public String getReceiverMobileNo() {
        return receiverMobileNo;
    }

    public AddressDTO setReceiverMobileNo(String receiverMobileNo) {
        this.receiverMobileNo = receiverMobileNo;
        return this;
    }

    public String getReceiverAddress() {
        return receiverAddress;
    }

    public AddressDTO setReceiverAddress(String receiverAddress) {
        this.receiverAddress = receiverAddress;
        return this;
    }

    public Integer getIsDefault() {
        return isDefault;
    }

    public AddressDTO setIsDefault(Integer isDefault) {
        this.isDefault = isDefault;
        return this;
    }
}
