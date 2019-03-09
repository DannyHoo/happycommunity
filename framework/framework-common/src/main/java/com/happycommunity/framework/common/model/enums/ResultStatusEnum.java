package com.happycommunity.framework.common.model.enums;

/**
 * @author Danny
 * @Title: ResultStatusEnum
 * @Description:
 * @Created on 2018-11-23 16:42:53
 */
public enum  ResultStatusEnum implements ResultStatusEnumInterface {

    //10开头为系统类型
    SUCCESS(100000, "处理成功"),
    //业务失败情况
    FAILURE(100001, "服务器正忙,请稍后重试"),
    //系统出现异常情况
    UNKOWN_SYS_ERROR(100002, "服务器正忙,请稍后重试"),
    //必须的参数为空
    PARAMETER_IS_NULL(100003,"参数为空"),

    //登录注册 200000~200999
    USER_ALREADY_EXIST(200000,"用户名已存在"),
    USERNAME_OR_PASSWORD_INVALID(200010,"用户名或密码错误"),
    USER_NOT_LOGIN(200020,"用户尚未登录，请登录系统"),
    USER_NOT_EXIST(200020,"用户不存在"),

    //下单 201000~201999
    USER_ADDRESS_NOT_EXIST(201000,"用户收货人地址不存在"),
    GOODS_NOT_EXIST(201010,"商品不存在"),
    GOODS_BALANCE_NOT_ENOUGH(201010,"商品库存不足"),
    GOODS_BALANCE_UPDATE_FAILURE(201010,"商品库存更新失败"),
    ;
    private int code;
    private String description;

    ResultStatusEnum(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public String getName() {
        return this.toString();
    }

    public int getCode() {
        return code;
    }

    public ResultStatusEnum setCode(int code) {
        this.code = code;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public ResultStatusEnum setDescription(String description) {
        this.description = description;
        return this;
    }
}
