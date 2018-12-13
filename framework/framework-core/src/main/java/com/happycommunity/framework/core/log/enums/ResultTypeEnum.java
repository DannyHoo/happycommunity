package com.happycommunity.framework.core.log.enums;

/**
 * @author dannyhoo
 * @Title: ResultTypeEnum
 * @Description:
 * @Created on 2018-06-11 23:59:07
 */
public enum ResultTypeEnum {
    BizSuccess("业务成功", "bizSuccess"),
    BizFail("业务失败", "bizFail"),
    Exception("业务发生异常", "exception"),
    Noset("没有设置", "noset");

    private String name;
    private String key;

    private ResultTypeEnum(String name, String key) {
        this.name = name;
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public String getKey() {
        return key;
    }
}
