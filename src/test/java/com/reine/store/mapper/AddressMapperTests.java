package com.reine.store.mapper;

import com.reine.store.entity.Address;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author reine
 * 2022/5/7 20:09
 */
@SpringBootTest
public class AddressMapperTests {

    @Autowired
    private AddressMapper addressMapper;

    @Test
    void insert() {
        Address address = new Address();
        address.setUid(13);
        address.setPhone("13222222222");
        address.setName("张三");
        addressMapper.insert(address);
    }

    @Test
    void countByUid() {
        Integer count = addressMapper.countByUid(13);
        System.out.println(count);
    }

    @Test
    void findByUid() {
        List<Address> addresses = addressMapper.findByUid(9);
        addresses.forEach(System.out::println);
    }

    @Test
    void findByAid() {
        System.out.println(addressMapper.findByAid(4));
    }

    @Test
    void updateNonDefault() {
        addressMapper.updateNonDefault(9);
    }

    @Test
    void updateDefaultByAid() {
        Address address = new Address();
        address.setAid(4);
        address.setIsDefault(1);
        address.setModifiedUser("系统管理员");
        address.setModifiedTime(LocalDateTime.now());
        addressMapper.updateByAid(address);
    }

    @Test
    void deleteByAid(){
        addressMapper.deleteByAid(1);
    }

    @Test
    void findLastModified(){
        System.out.println(addressMapper.findLastModified(9));
    }
}
