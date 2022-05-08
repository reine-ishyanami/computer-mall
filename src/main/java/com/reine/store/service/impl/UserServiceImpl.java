package com.reine.store.service.impl;

import com.reine.store.entity.User;
import com.reine.store.mapper.UserMapper;
import com.reine.store.service.IUserService;
import com.reine.store.service.ex.*;
import com.reine.store.vo.UserVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * @author reine
 * 2022/5/6 16:33
 */
@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserMapper userMapper;

    /**
     * 用户注册
     *
     * @param user 用户数据
     */
    @Override
    public void register(User user) {
        // 调用findByUsername判断用户是否被注册过
        User result = userMapper.findByUsername(user.getUsername());
        // 如果结果集不为空，则用户注册失败
        if (result != null) {
            throw new UsernameDuplicateException("用户名被占用");
        }

        // 密码加密
        String oldPassword = user.getPassword();
        String salt = UUID.randomUUID().toString().toUpperCase();
        String md5Password = getMD5Password(oldPassword, salt);

        // 重新补全密码
        user.setSalt(salt);
        user.setPassword(md5Password);

        // 数据补全
        user.setIsDeleted(0);
        user.setCreatedUser(user.getUsername());
        user.setModifiedUser(user.getUsername());
        LocalDateTime now = LocalDateTime.now();
        user.setCreatedTime(now);
        user.setModifiedTime(now);

        // 插入数据
        Integer rows = userMapper.insert(user);
        if (rows != 1) {
            throw new InsertException("在注册过程中产生了未知异常");
        }

    }

    /**
     * 用户登录
     *
     * @param username 用户名
     * @param password 密码
     * @return 用户数据
     */
    @Override
    public UserVo login(String username, String password) {
        User result = userMapper.findByUsername(username);
        if (result == null) {
            throw new UserNotFoundException("用户未注册，请先注册");
        }
        String salt = result.getSalt();
        String passwordInDb = result.getPassword();
        if (result.getIsDeleted() == 1) {
            throw new UserNotFoundException("用户未注册，请先注册");
        }
        // 比较密码
        if (!getMD5Password(password, salt).equals(passwordInDb)) {
            throw new PasswordNotMatchException("用户密码错误");
        }
        UserVo user = new UserVo();
        BeanUtils.copyProperties(result, user, "email", "phone", "gender");
        return user;
    }

    /**
     * 修改密码
     *
     * @param uid         用户id
     * @param username    用户名
     * @param oldPassword 用户旧密码
     * @param newPassword 用户新密码
     */
    @Override
    public void updatePassword(Integer uid, String username, String oldPassword, String newPassword) {
        User result = userMapper.findByUid(uid);
        if (result == null || result.getIsDeleted() == 1) {
            throw new UserNotFoundException("用户不存在");
        }
        // 密码比较
        if (!getMD5Password(oldPassword, result.getSalt()).equals(result.getPassword())) {
            throw new PasswordNotMatchException("密码错误");
        }
        // 将新密码设置到数据库中
        String newMd5Password = getMD5Password(newPassword, result.getSalt());
        User user = new User();
        user.setUid(uid);
        user.setPassword(newMd5Password);
        user.setModifiedUser(username);
        user.setModifiedTime(LocalDateTime.now());
        Integer rows = userMapper.updateByUid(user);
        if (rows != 1) {
            throw new UpdateException("更新数据异常");
        }
    }

    /**
     * 获取用户数据
     *
     * @param uid 用户uid
     * @return 用户数据
     */
    @Override
    public UserVo getUserInfoByUid(Integer uid) {
        User result = userMapper.findByUid(uid);
        if (result == null || result.getIsDeleted() == 1) {
            throw new UserNotFoundException("用户不存在");
        }
        UserVo user = new UserVo();
        BeanUtils.copyProperties(result, user, "uid", "avatar");
        return user;
    }

    /**
     * 更新用户信息
     *
     * @param uid      用户uid
     * @param username 用户名
     * @param user     用户信息
     */
    @Override
    public void updateInfo(Integer uid, String username, User user) {
        User result = userMapper.findByUid(uid);
        if (result == null || result.getIsDeleted() == 1) {
            throw new UserNotFoundException("用户不存在");
        }
        user.setUid(uid);
        user.setModifiedUser(username);
        user.setModifiedTime(LocalDateTime.now());
        Integer rows = userMapper.updateByUid(user);
        if (rows != 1) {
            throw new UpdateException("更新数据异常");
        }
    }

    /**
     * 更新用户头像
     *
     * @param uid      用户id
     * @param username 更新人
     * @param avatar   用户头像地址
     */
    @Override
    public void updateAvatar(Integer uid, String username, String avatar) {
        // 查询当前的用户数据是否存在
        User result = userMapper.findByUid(uid);
        if (result == null || result.getIsDeleted() == 1) {
            throw new UserNotFoundException("用户不存在");
        }
        User user = new User();
        user.setUid(uid);
        user.setAvatar(avatar);
        user.setModifiedUser(username);
        user.setModifiedTime(LocalDateTime.now());
        Integer rows = userMapper.updateByUid(user);
        if (rows != 1) {
            throw new UpdateException("更新数据异常");
        }
    }

    /**
     * 由密码和盐值生成新密码后进行md5加密
     *
     * @param password 密码
     * @param salt     盐值
     */
    private String getMD5Password(String password, String salt) {
        // 密码加密
        for (int i = 0; i < 3; i++) {
            password = DigestUtils.md5DigestAsHex((salt + password + salt).getBytes()).toUpperCase();
        }
        return password;
    }
}

