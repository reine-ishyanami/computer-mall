package com.reine.store.vo;

import lombok.Getter;

/**
 * @author reine
 * 2022/5/6 20:41
 */
@Getter
public enum ErrorCode {

    /**
     * 错误类型
     */
    USERNAME_DUPLICATE(4051, "用户已存在"),
    INSERT_USER_ERROR(4052, "在注册过程中产生了未知异常"),
    USER_NOT_FOUND(4053, "用户不存在"),
    PASSWORD_NOT_MATCH(4054, "密码错误"),
    UPDATE_ERROR(4055, "更新异常"),
    ADDRESS_COUNT_LIMIT(4056, "用户地址超出上限"),
    ADDRESS_NOT_FOUND(4057, "用户地址未找到"),
    DELETE_ERROR(4058, "删除异常"),
    FILE_EMPTY_ERROR(4061, "上传文件为空"),
    FILE_SIZE_ERROR(4062, "文件过大"),
    FILE_TYPE_ERROR(4063, "文件类型错误"),
    FILE_STATE_ERROR(4064, "文件状态异常"),
    FILE_UPLOAD_IO_ERROR(4065, "文件读写异常"),
    ACCESS_DENIED(4071, "非法访问"),
    PRODUCT_NOT_FOUND(4081, "商品不存在"),
    CART_NOT_FOUND(4091, "购物车数据不存在");

    /**
     * 错误码
     */
    private Integer code;
    /**
     * 错误信息
     */
    private String massage;

    ErrorCode(Integer code, String massage) {
        this.code = code;
        this.massage = massage;
    }

}
