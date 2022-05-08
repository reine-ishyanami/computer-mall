package com.reine.store.service;

import com.reine.store.entity.Address;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * 地址测试类
 *
 * @author reine
 * 2022/5/7 20:38
 */
@SpringBootTest
public class AddressServiceTests {

    @Autowired
    private IAddressService addressService;

    @Test
    void addNewAddress() {
        Address address = new Address();
        address.setPhone("13655556666");
        address.setName("李四");
        addressService.addNewAddress(13, "系统管理员", address);
    }

    @Test
    void getByUid(){
        System.out.println(addressService.getByUid(9));
    }

    @Test
    void setDefault(){
        addressService.setDefault(4,9,"系统管理员");
    }

    @Test
    void delete(){
        addressService.delete(2,13,"系统管理员");
    }

}
