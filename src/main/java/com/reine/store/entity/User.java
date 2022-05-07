package com.reine.store.entity;

import lombok.*;

/**
 * @author reine
 * @since 2022/5/6 14:35
 */
@Data
public class User extends BaseEntity {

    private Integer uid;

    private String username;

    private String password;

    private String salt;

    private String phone;

    private String email;

    private Integer gender;

    private String avatar;

    private Integer isDeleted;

}
