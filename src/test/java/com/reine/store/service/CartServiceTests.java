package com.reine.store.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author reine
 * 2022/5/8 9:25
 */
@SpringBootTest
public class CartServiceTests {

    @Autowired
    private ICartService cartService;

    @Test
    void addToCart(){
        cartService.addToCart(9,10000002,10,"系统管理员");
    }

}
