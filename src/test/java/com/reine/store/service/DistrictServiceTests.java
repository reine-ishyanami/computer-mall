package com.reine.store.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author reine
 * 2022/5/8 9:25
 */
@SpringBootTest
public class DistrictServiceTests {

    @Autowired
    private IDistrictService districtService;

    @Test
    void getByParent() {
        districtService.getByParent("86").forEach(System.out::println);
    }

}
