package com.reine.store.vo;

import lombok.Getter;

/**
 * @author reine
 * @since 2022/5/6 20:41
 */
@Getter
public enum ErrorCode {

    /**
     * 错误类型
     */
    USERNAME_DUPLICATE(451, "用户已存在"),
    INSERT_USER_ERROR(452, "在注册过程中产生了未知异常"),
    USER_NOT_FOUND(453, "用户不存在"),
    PASSWORD_NOT_MATCH(454, "密码错误"),
    UPDATE_ERROR(455, "更新异常"),
    FILE_EMPTY_ERROR(461, "上传文件为空"),
    FILE_SIZE_ERROR(462, "文件过大"),
    FILE_TYPE_ERROR(463, "文件类型错误"),
    FILE_STATE_ERROR(464, "文件状态异常"),
    FILE_UPLOAD_IO_ERROR(465, "文件读写异常");

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
