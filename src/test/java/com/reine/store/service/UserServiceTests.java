package com.reine.store.service;

import com.reine.store.entity.User;
import com.reine.store.service.ex.ServiceException;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * 用户测试类
 *
 * @author reine
 * @since 2022/5/6 16:47
 */
@Slf4j
@SpringBootTest
public class UserServiceTests {

    @Autowired
    private IUserService userService;

    @Test
    void register() {
        try {
            User user = new User();
            user.setUsername("reine");
            user.setPassword("123456");
            userService.register(user);
            log.info("OK");
        } catch (ServiceException e) {
            e.printStackTrace();
            log.error("errorMessage---{}",e.getMessage());
        }

    }

    @Test
    void login() {
        User user = userService.login("admin", "123456");
        log.info("user---{}",user);
    }

    @Test
    void updatePassword() {
        userService.updatePassword(13, "系统管理员", "123456", "654321");
    }

    @Test
    void getUserInfoByUid() {
        System.out.println(userService.getUserInfoByUid(13));
    }

    @Test
    void updateInfo() {
        User user = new User();
        user.setUsername("reine");
        user.setPhone("13544445558");
        user.setEmail("1244@qq.xx");
        user.setGender(1);
        userService.updateInfo(13, "系统管理员", user);
    }

    @Test
    void updateAvatar() {
        userService.updateAvatar(13, "系统管理员", "/upload/avatar");
    }
}
