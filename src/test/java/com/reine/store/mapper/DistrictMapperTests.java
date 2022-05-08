package com.reine.store.mapper;

import com.reine.store.entity.District;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @author reine
 * 2022/5/8 9:02
 */
@SpringBootTest
public class DistrictMapperTests {

    @Autowired
    private DistrictMapper districtMapper;

    @Test
    void findByParent() {
        List<District> districts = districtMapper.findByParent("210100");
        districts.forEach(System.out::println);
    }

    @Test
    void findNameByCode() {
        String name = districtMapper.findNameByCode("610000");
        System.out.println(name);
    }

}
