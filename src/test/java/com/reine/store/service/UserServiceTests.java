package com.reine.store.service;

import com.reine.store.entity.User;
import com.reine.store.service.ex.ServiceException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * 用户测试类
 *
 * @author reine
 * @since 2022/5/6 16:47
 */
@SpringBootTest
public class UserServiceTests {

    @Autowired
    private IUserService userService;

    @Test
    void register() {
        try {
            User user = new User();
            user.setUsername("李四");
            user.setPassword("123456");
            userService.register(user);
            System.out.println("OK");
        } catch (ServiceException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }

    }


    @Test
    void login(){
        User user = userService.login("admin", "123456");
        System.out.println(user);
    }
}
