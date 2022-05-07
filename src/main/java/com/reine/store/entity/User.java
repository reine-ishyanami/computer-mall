package com.reine.store.entity;

import lombok.*;

/**
 * @author reine
 * @since 2022/5/6 14:35
 */
@Data
public class User extends BaseEntity {

    /**
     * 用户id
     */
    private Integer uid;

    /**
     * 用户名
     */
    private String username;

    /**
     * 用户加密密码
     */
    private String password;

    /**
     * 盐值
     */
    private String salt;

    /**
     * 用户手机号
     */
    private String phone;

    /**
     * 用户邮箱
     */
    private String email;

    /**
     * 用户性别
     */
    private Integer gender;

    /**
     * 用户头像
     */
    private String avatar;

    /**
     * 删除标志位
     */
    private Integer isDeleted;

}
