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
    private String mobile;
    private String address;

    public String getUserName() {
        return userName;
    }

    public AddressDO setUserName(String userName) {
        this.userName = userName;
        return this;
    }

    public String getMobile() {
        return mobile;
    }

    public AddressDO setMobile(String mobile) {
        this.mobile = mobile;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public AddressDO setAddress(String address) {
        this.address = address;
        return this;
    }
}
