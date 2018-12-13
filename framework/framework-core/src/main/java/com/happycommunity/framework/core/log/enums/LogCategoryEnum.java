package com.happycommunity.framework.core.log.enums;

/**
 * @author dannyhoo
 * @Title: LogCategoryEnum
 * @Description:
 * @Created on 2018-06-11 23:50:30
 */
public enum LogCategoryEnum {
    Method("方法级日志", "method"),
    Business("业务日志", "business"),
    Security("安全日志", "security"),
    Limit("限制日志", "limit");

    private String name;
    private String key;

    private LogCategoryEnum(String name, String key) {
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
