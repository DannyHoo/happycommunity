package com.happycommunity.framework.common.model.result;

import com.happycommunity.framework.common.model.enums.ResultStatusEnum;
import com.happycommunity.framework.common.model.enums.ResultStatusEnumInterface;

import java.io.Serializable;

/**
 * @author Danny
 * @Title: ServiceResult
 * @Description:
 * @Created on 2018-11-29 12:56:31
 */
public class ServiceResult<R> implements Serializable {

    private static final long serialVersionUID = 7567039181931362380L;

    private ResultStatusEnumInterface resultStatusEnum;
    private R data;

    public R getData() {
        return data;
    }

    public ServiceResult setData(R data) {
        this.data = data;
        return this;
    }

    public ServiceResult() {
    }

    public ServiceResult(ResultStatusEnumInterface resultStatusEnum) {
        this.resultStatusEnum = resultStatusEnum;
    }

    public ServiceResult(ResultStatusEnumInterface resultStatusEnum, R data) {
        this.resultStatusEnum = resultStatusEnum;
        this.data = data;
    }

    public ResultStatusEnumInterface getResultStatusEnum() {
        return resultStatusEnum;
    }

    public ServiceResult setResultStatusEnum(ResultStatusEnumInterface resultStatusEnum) {
        this.resultStatusEnum = resultStatusEnum;
        return this;
    }

    public boolean isSuccess() {
        return resultStatusEnum== ResultStatusEnum.SUCCESS;
    }

    public boolean isFail() {
        return resultStatusEnum!= ResultStatusEnum.SUCCESS;
    }
}
