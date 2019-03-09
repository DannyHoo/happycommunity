package com.happycommunity.user.domain;

import com.happycommunity.framework.common.model.domain.BaseDO;

/**
 * @author Danny
 * @Title: AddressDAO
 * @Description:
 * @Created on 2018-11-27 17:54:02
 */
public class AddressDO extends BaseDO {

    private String userName;
    private String receiverName;
    private String receiverMobileNo;
    private String receiverAddress;
    private Integer isDefault;

    public String getUserName() {
        return userName;
    }

    public AddressDO setUserName(String userName) {
        this.userName = userName;
        return this;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public AddressDO setReceiverName(String receiverName) {
        this.receiverName = receiverName;
        return this;
    }

    public String getReceiverMobileNo() {
        return receiverMobileNo;
    }

    public AddressDO setReceiverMobileNo(String receiverMobileNo) {
        this.receiverMobileNo = receiverMobileNo;
        return this;
    }

    public String getReceiverAddress() {
        return receiverAddress;
    }

    public AddressDO setReceiverAddress(String receiverAddress) {
        this.receiverAddress = receiverAddress;
        return this;
    }

    public Integer getIsDefault() {
        return isDefault;
    }

    public AddressDO setIsDefault(Integer isDefault) {
        this.isDefault = isDefault;
        return this;
    }
}
