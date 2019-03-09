package com.happycommunity.framework.common.model.enums;

/**
 * @author Danny
 * @Title: YesNoEnum
 * @Description:
 * @Created on 2019-02-26 11:06:40
 */
public enum YesNoEnum {

    YES(10, "是"),
    NO(20, "否");

    private int code;
    private String description;

    YesNoEnum(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public int getCode() {
        return code;
    }

    public YesNoEnum setCode(int code) {
        this.code = code;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public YesNoEnum setDescription(String description) {
        this.description = description;
        return this;
    }

}
