package com.happycommunity.user.domain;

import com.happycommunity.framework.common.model.domain.BaseDO;

/**
 * @author Danny
 * @Title: UserDO
 * @Description:
 * @Created on 2018-11-27 11:03:57
 */
public class UserDO extends BaseDO{

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

    public UserDO setUserName(String userName) {
        this.userName = userName;
        return this;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public UserDO setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserDO setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserDO setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getSalt() {
        return salt;
    }

    public UserDO setSalt(String salt) {
        this.salt = salt;
        return this;
    }

    public String getRealName() {
        return realName;
    }

    public UserDO setRealName(String realName) {
        this.realName = realName;
        return this;
    }

    public String getIdCardNo() {
        return idCardNo;
    }

    public UserDO setIdCardNo(String idCardNo) {
        this.idCardNo = idCardNo;
        return this;
    }
}
