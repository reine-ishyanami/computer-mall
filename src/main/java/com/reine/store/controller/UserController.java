package com.reine.store.controller;

import com.reine.store.controller.ex.*;
import com.reine.store.entity.User;
import com.reine.store.service.IUserService;
import com.reine.store.vo.JsonResult;
import com.reine.store.vo.UserVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Set;
import java.util.UUID;

/**
 * 用户控制器
 *
 * @author reine
 * 2022/5/6 17:24
 */
@RestController
@RequestMapping("user")
@Slf4j
public class UserController extends BaseController {

    @Autowired
    private IUserService userService;

    /**
     * 文件类型限制
     */
    @Value("#{'${multipart.type}'.split(',')}")
    private Set<String> avatarType;

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
    public JsonResult<UserVo> login(String username, String password, HttpSession session) {
        UserVo user = userService.login(username, password);
        session.setAttribute("uid", user.getUid());
        session.setAttribute("username", user.getUsername());
        log.info("uid--{}", getUidFromSession(session));
        log.info("username--{}", getUsernameFromSession(session));
        return new JsonResult<>(OK, user);
    }

    /**
     * 修改密码log
     *
     * @param oldPassword 旧密码
     * @param newPassword 新密码
     * @param session     session对象
     * @return 返回信息
     */
    @RequestMapping("change_password")
    public JsonResult<Void> changePassword(String oldPassword, String newPassword, HttpSession session) {
        Integer uid = getUidFromSession(session);
        String username = getUsernameFromSession(session);
        userService.updatePassword(uid, username, oldPassword, newPassword);
        return new JsonResult<>(OK);
    }

    /**
     * 根据用户uid获取用户数据
     *
     * @param session session对象
     * @return 返回信息
     */
    @RequestMapping("get_by_uid")
    public JsonResult<UserVo> getByUid(HttpSession session) {
        UserVo user = userService.getUserInfoByUid(getUidFromSession(session));
        return new JsonResult<>(OK, user);
    }

    /**
     * 修改用户信息
     *
     * @param user    用户信息
     * @param session session对象
     * @return 返回信息
     */
    @RequestMapping("change_info")
    public JsonResult<Void> changeInfo(User user, HttpSession session) {
        Integer uid = getUidFromSession(session);
        // 获取修改人姓名
        String username = getUsernameFromSession(session);
        userService.updateInfo(uid, username, user);
        return new JsonResult<>(OK);
    }

    /**
     * 修改头像
     *
     * @param session session对象
     * @param file    头像图片
     * @return 头像地址
     */
    @RequestMapping("change_avatar")
    public JsonResult<String> changeAvatar(HttpSession session, MultipartFile file) throws FileNotFoundException {
        if (file.isEmpty()) {
            throw new FileEmptyException("文件为空");
        }
        if (file.getSize() > 5 * 1024 * 1024) {
            throw new FileSizeException("文件过大");
        }
        String contentType = file.getContentType();
        if (!avatarType.contains(contentType)) {
            throw new FileTypeException("文件类型不支持");
        }
        // 上传的文件路径 /upload/file.png
        String parent = ResourceUtils.getURL("classpath:static").getPath() + "/upload";
        // File对象指向这个路径，File是否存在
        File dir = new File(parent);
        if (!dir.exists()) {
            // 不存在则创建目录
            dir.mkdirs();
        }
        // 使用UUID上传字符串作为文件的文件名
        String originalFilename = file.getOriginalFilename();
        log.info("originalFilename---{}", originalFilename);
        int index = originalFilename.lastIndexOf(".");
        String suffix = originalFilename.substring(index);
        String filename = UUID.randomUUID().toString().toUpperCase() + suffix;

        // 要创建的文件全限定名
        File dest = new File(dir, filename);
        // 写入文件
        try {
            file.transferTo(dest);
        } catch (FileStateException e) {
            throw new FileStateException("文件状态异常");
        } catch (IOException e) {
            throw new FileUploadIOException("文件读写异常");
        }
        Integer uid = getUidFromSession(session);
        String username = getUsernameFromSession(session);
        String avatar = "/upload/" + filename;
        userService.updateAvatar(uid, username, avatar);

        return new JsonResult<>(OK, avatar);
    }
}
