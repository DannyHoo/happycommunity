package com.happycommunity.gateway.request;

import java.io.Serializable;

/**
 * @author Danny
 * @Title: CommonRequest
 * @Description:
 * @Created on 2018-11-28 23:03:50
 */
public class CommonRequest implements Serializable {

    private static final long serialVersionUID = -5504034338352273139L;

    private String token;

    public String getToken() {
        return token;
    }

    public CommonRequest setToken(String token) {
        this.token = token;
        return this;
    }
}
