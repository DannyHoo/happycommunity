package com.happycommunity.framework.common.model.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Danny
 * @Title: BaseDTO
 * @Description:
 * @Created on 2018-11-26 15:18:06
 */
public class BaseDTO implements Serializable {
    private static final long serialVersionUID = 5231134212346077681L;

    private Long id;

    private Date createTime;

    private Date updateTime;

    private String comment;

    public Long getId() {
        return id;
    }

    public BaseDTO setId(Long id) {
        this.id = id;
        return this;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public BaseDTO setCreateTime(Date createTime) {
        this.createTime = createTime;
        return this;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public BaseDTO setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
        return this;
    }

    public String getComment() {
        return comment;
    }

    public BaseDTO setComment(String comment) {
        this.comment = comment;
        return this;
    }
}
