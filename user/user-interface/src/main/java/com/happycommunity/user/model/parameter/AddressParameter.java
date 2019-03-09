package com.happycommunity.user.model.parameter;

import com.happycommunity.framework.common.model.parameter.BaseParameter;

/**
 * @author Danny
 * @Title: AddressParameter
 * @Description:
 * @Created on 2019-02-25 18:25:42
 */
public class AddressParameter extends BaseParameter {

    private String userName;
    private String receiverName;
    private String receiverMobileNo;
    private String receiverAddress;
    private Integer isDefault;

    public String getUserName() {
        return userName;
    }

    public AddressParameter setUserName(String userName) {
        this.userName = userName;
        return this;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public AddressParameter setReceiverName(String receiverName) {
        this.receiverName = receiverName;
        return this;
    }

    public String getReceiverMobileNo() {
        return receiverMobileNo;
    }

    public AddressParameter setReceiverMobileNo(String receiverMobileNo) {
        this.receiverMobileNo = receiverMobileNo;
        return this;
    }

    public String getReceiverAddress() {
        return receiverAddress;
    }

    public AddressParameter setReceiverAddress(String receiverAddress) {
        this.receiverAddress = receiverAddress;
        return this;
    }

    public Integer getIsDefault() {
        return isDefault;
    }

    public AddressParameter setIsDefault(Integer isDefault) {
        this.isDefault = isDefault;
        return this;
    }
}
