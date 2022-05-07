package com.reine.store.controller;

import com.reine.store.entity.User;
import com.reine.store.service.IUserService;
import com.reine.store.util.JsonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;

import javax.servlet.http.HttpSession;

/**
 * @author reine
 * @since 2022/5/6 17:24
 */
@RestController
@RequestMapping("user")
@Slf4j
public class UserController extends BaseController {

    @Autowired
    private IUserService userService;

    /**
     * 用户注册
     *
     * @param user 用户信息
     * @return 返回信息
     */
    @RequestMapping("register")
    public JsonResult<Void> register(User user) {
        userService.register(user);
        return new JsonResult<>(OK);
    }

    /**
     * 用户登录
     *
     * @param username 用户名
     * @param password 用户密码
     * @param session  session对象
     * @return 返回信息
     */
    @RequestMapping("login")
    public JsonResult<User> login(String username, String password, HttpSession session) {
        User user = userService.login(username, password);
        session.setAttribute("uid", user.getUid());
        session.setAttribute("username", user.getUsername());
        log.info("uid--{}", getUidFromSession(session));
        log.info("username--{}", getUsernameFromSession(session));
        return new JsonResult<>(OK, user);
    }

}
