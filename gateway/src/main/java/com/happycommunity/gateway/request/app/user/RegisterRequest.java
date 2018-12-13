package com.happycommunity.gateway.request.app.user;

import com.happycommunity.gateway.request.CommonRequest;

/**
 * @author Danny
 * @Title: RegisterRequest
 * @Description:
 * @Created on 2018-11-29 12:47:05
 */
public class RegisterRequest extends CommonRequest {
    private String userName;
    private String password;

    public String getUserName() {
        return userName;
    }

    public RegisterRequest setUserName(String userName) {
        this.userName = userName;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public RegisterRequest setPassword(String password) {
        this.password = password;
        return this;
    }
}
