package com.happycommunity.user.model.parameter;

import com.happycommunity.framework.common.model.parameter.BaseParameter;

/**
 * @author Danny
 * @Title: UserParameter
 * @Description:
 * @Created on 2018-12-08 13:14:35
 */
public class UserParameter extends BaseParameter {
    private String userName;
    private String mobileNo;
    private String salt;
    private String password;
    private String email;
    private String realName;
    private String idCardNo;

    public String getUserName() {
        return userName;
    }

    public UserParameter setUserName(String userName) {
        this.userName = userName;
        return this;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public UserParameter setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
        return this;
    }

    public String getSalt() {
        return salt;
    }

    public UserParameter setSalt(String salt) {
        this.salt = salt;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserParameter setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserParameter setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getRealName() {
        return realName;
    }

    public UserParameter setRealName(String realName) {
        this.realName = realName;
        return this;
    }

    public String getIdCardNo() {
        return idCardNo;
    }

    public UserParameter setIdCardNo(String idCardNo) {
        this.idCardNo = idCardNo;
        return this;
    }
}
