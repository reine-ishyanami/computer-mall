package com.reine.store.mapper;

import com.reine.store.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.time.LocalDateTime;

/**
 * SpringBootTest注解 标注当前类为测试类。项目打包时不会包含
 *
 * @author reine
 * @since 2022/5/6 15:52
 */
@Slf4j
@SpringBootTest
public class UserMapperTests {

    @Resource
    private UserMapper userMapper;

    /**
     * 单元测试方法，可以独立运行，不用启动整个项目<p>
     * 1. 必须被@Test修饰<p>
     * 2. 返回值类型必须是void<p>
     * 3. 方法的参数列表不能指定任何类型<p>
     * 4. 方法的访问修饰符必须是public
     */
    @Test
    void insert() {
        User user = new User();
        user.setUsername("张三");
        user.setPassword("123456");
        Integer rows = userMapper.insert(user);
        log.info("rows---{}",rows);
    }

    @Test
    void findByUsername() {
        User user = userMapper.findByUsername("张三");
        log.info("user---{}",user);
    }

    @Test
    void updatePasswordByUid() {
        User user = new User();
        user.setUid(12);
        user.setPassword("12345");
        user.setModifiedUser("系统管理员");
        user.setModifiedTime(LocalDateTime.now());
        userMapper.updateByUid(user);
    }

    @Test
    void updateInfoByUid() {
        User user = new User();
        user.setUid(12);
        user.setPhone("13644444444");
        user.setEmail("111@qq.cn");
        user.setGender(1);
        user.setModifiedUser("系统管理员");
        user.setModifiedTime(LocalDateTime.now());
        userMapper.updateByUid(user);
    }

    @Test
    void updateAvatarByUid() {
        User user = new User();
        user.setUid(12);
        user.setAvatar("/images/avatar.jpg");
        user.setModifiedUser("系统管理员");
        user.setModifiedTime(LocalDateTime.now());
        userMapper.updateByUid(user);
    }

    @Test
    void findByUid() {
        System.out.println(userMapper.findByUid(6));
    }
}
