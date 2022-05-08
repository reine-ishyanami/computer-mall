package com.reine.store.vo;

import lombok.Data;

/**
 * @author reine
 * 2022/5/8 12:01
 */
@Data
public class UserVo {

    /**
     * 用户id
     */
    private Integer uid;

    /**
     * 用户名
     */
    private String username;

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

}
