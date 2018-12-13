package com.happycommunity.gateway.response;


import com.happycommunity.framework.common.model.enums.ResultStatusEnumInterface;
import com.happycommunity.framework.common.model.result.ServiceResult;

/**
 * @author Danny
 * @Title: ResponseData
 * @Description:
 * @Created on 2018-11-23 16:37:44
 */
public class ResponseData {
    private int code;
    private String message;
    private Object data;

    public ResponseData() {
    }

    public ResponseData(ResultStatusEnumInterface resultStatusEnumInterface, Object data) {
        this.code=resultStatusEnumInterface.getCode();
        this.message=resultStatusEnumInterface.getDescription();
        this.data = data;
    }

    public ResponseData(ServiceResult registerResult) {
        this.code=registerResult.getResultStatusEnum().getCode();
        this.message=registerResult.getResultStatusEnum().getDescription();
        this.data = registerResult.getData();
    }

    public int getCode() {
        return code;
    }

    public ResponseData setCode(int code) {
        this.code = code;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public ResponseData setMessage(String message) {
        this.message = message;
        return this;
    }

    public Object getData() {
        return data;
    }

    public ResponseData setData(Object data) {
        this.data = data;
        return this;
    }
}
