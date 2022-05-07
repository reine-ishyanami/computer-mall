package com.reine.store.vo;

import lombok.Getter;

/**
 * @author reine
 * @since 2022/5/6 20:41
 */
@Getter
public enum ErrorCode {

    USERNAME_DUPLICATE(451, "用户已存在"),
    INSERT_USER_ERROR(452, "在注册过程中产生了未知异常"),
    USER_NOT_FOUND(453, "用户不存在"),
    PASSWORD_NOT_MATCH(454, "密码错误");

    private Integer code;
    private String massage;

    ErrorCode(Integer code, String massage) {
        this.code = code;
        this.massage = massage;
    }

}
