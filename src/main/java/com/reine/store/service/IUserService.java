package com.reine.store.service;

import com.reine.store.entity.User;
import com.reine.store.vo.UserVo;

/**
 * 用户模块业务层接口
 *
 * @author reine
 * 2022/5/6 16:31
 */
public interface IUserService {
    /**
     * 用户注册
     *
     * @param user 用户数据
     */
    void register(User user);

    /**
     * 用户登录
     *
     * @param username 用户名
     * @param password 密码
     * @return 用户数据
     */
    UserVo login(String username, String password);

    /**
     * 修改密码
     *
     * @param uid         用户id
     * @param username    用户名
     * @param oldPassword 用户旧密码
     * @param newPassword 用户新密码
     */
    void updatePassword(Integer uid, String username, String oldPassword, String newPassword);

    /**
     * 获取用户数据
     *
     * @param uid 用户uid
     * @return 用户数据
     */
    UserVo getUserInfoByUid(Integer uid);

    /**
     * 更新用户信息
     *
     * @param uid      用户uid
     * @param username 更新人
     * @param user     用户信息
     */
    void updateInfo(Integer uid, String username, User user);

    /**
     * 更新用户头像
     *
     * @param uid      用户id
     * @param username 更新人
     * @param avatar   用户头像地址
     */
    void updateAvatar(Integer uid, String username, String avatar);

}
