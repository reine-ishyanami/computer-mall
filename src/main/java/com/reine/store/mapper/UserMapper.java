package com.reine.store.mapper;

import com.reine.store.entity.User;

/**
 * 用户模块持久层接口
 * @author reine
 * @since 2022/5/6 14:59
 */
public interface UserMapper {
    /**
     * 插入用户数据
     * @param user 用户数据
     * @return 受影响的行数
     */
    Integer insert(User user);

    /**
     * 查询用户数据
     * @param username 用户信息
     * @return 用户数据
     */
    User findByUsername(String  username);
}
