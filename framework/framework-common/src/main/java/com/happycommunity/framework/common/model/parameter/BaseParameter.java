package com.happycommunity.framework.common.model.parameter;

import com.happycommunity.framework.common.model.model.GlobalTraceData;

import java.util.Date;

/**
 * @author Danny
 * @Title: BaseParameter
 * @Description:
 * @Created on 2018-11-29 12:56:52
 */
public class BaseParameter extends GlobalTraceData {

    private Long id;
    private Date createTime;
    private Date updateTime;

    public Long getId() {
        return id;
    }

    public BaseParameter setId(Long id) {
        this.id = id;
        return this;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public BaseParameter setCreateTime(Date createTime) {
        this.createTime = createTime;
        return this;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public BaseParameter setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
        return this;
    }
}
