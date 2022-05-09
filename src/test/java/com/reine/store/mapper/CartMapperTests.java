package com.reine.store.mapper;

import com.reine.store.entity.Cart;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

/**
 * @author reine
 * 2022/5/7 20:09
 */
@SpringBootTest
public class CartMapperTests {

    @Autowired
    private CartMapper cartMapper;

    @Test
    void insert() {
        Cart cart = new Cart();
        cart.setUid(9);
        cart.setPid(10000001);
        cart.setNum(10);
        cart.setPrice(1000L);
        cart.setCreatedUser("系统管理员");
        cart.setModifiedUser("系统管理员");
        LocalDateTime now = LocalDateTime.now();
        cart.setCreatedTime(now);
        cart.setModifiedTime(now);
        cartMapper.insert(cart);
    }

    @Test
    void updateNueByCid() {
        Cart cart = new Cart();
        cart.setCid(2);
        cart.setNum(9);
        cart.setModifiedUser("系统管理员");
        cart.setModifiedTime(LocalDateTime.now());
        cartMapper.updateCartByCid(cart);
    }

    @Test
    void findByCUidAndPid() {
        System.out.println(cartMapper.findByCUidAndPid(9, 10000001));
    }

    @Test
    void findVOByUid() {
        cartMapper.findVOByUid(9).forEach(System.out::println);
    }

    @Test
    void findByCid() {
        System.out.println(cartMapper.findByCid(2));
    }

    @Test
    void findVOByCids() {
        Integer[] cids = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        cartMapper.findVOByCids(cids).forEach(System.out::println);
    }
}
