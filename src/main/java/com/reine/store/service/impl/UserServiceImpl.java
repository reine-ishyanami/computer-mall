package com.reine.store.service.impl;

import com.reine.store.entity.User;
import com.reine.store.mapper.UserMapper;
import com.reine.store.service.IUserService;
import com.reine.store.service.ex.InsertException;
import com.reine.store.service.ex.UserNotFoundException;
import com.reine.store.service.ex.UsernameDuplicateException;
import com.reine.store.service.ex.PasswordNotMatchException;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * @author reine
 * @since 2022/5/6 16:33
 */
@Service
public class UserServiceImpl implements IUserService {

    @Resource
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
    public User login(String username, String password) {
        User user = userMapper.findByUsername(username);
        if (user == null) {
            throw new UserNotFoundException("用户未注册，请先注册");
        }
        String salt = user.getSalt();
        String passwordInDb = user.getPassword();
        if (user.getIsDeleted() == 1) {
            throw new UserNotFoundException("用户未注册，请先注册");
        }
        // 比较密码
        if (!getMD5Password(password, salt).equals(passwordInDb)) {
            throw new PasswordNotMatchException("用户密码错误");
        }
        User result = new User();
        result.setUid(user.getUid());
        result.setUsername(user.getUsername());
        result.setAvatar(user.getAvatar());

        return result;
    }

    /**
     * 由密码和盐值生成新密码后进行md5加密
     *
     * @param password
     * @param salt
     */
    private String getMD5Password(String password, String salt) {
        // 密码加密
        for (int i = 0; i < 3; i++) {
            password = DigestUtils.md5DigestAsHex((salt + password + salt).getBytes()).toUpperCase();
        }
        return password;
    }
}

