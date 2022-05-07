package com.reine.store.mapper;

import com.reine.store.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * SpringBootTest注解 标注当前类为测试类。项目打包时不会包含
 *
 * @author reine
 * @since 2022/5/6 15:52
 */
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
        System.out.println(rows);
    }

    @Test
    void findByUsername() {
        User user = userMapper.findByUsername("张三");
        System.out.println(user);
    }
}
