package com.reine.store.service;

import com.reine.store.entity.User;

/**
 * 用户模块业务层接口
 * @author reine
 * @since 2022/5/6 16:31
 */
public interface IUserService {
    /**
     * 用户注册
     * @param user 用户数据
     */
    void register(User user);

    /**
     * 用户登录
     * @param username 用户名
     * @param password 密码
     * @return 用户数据
     */
    User login(String username,String password);
}
