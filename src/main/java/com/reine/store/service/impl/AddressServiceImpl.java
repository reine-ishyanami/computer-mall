package com.reine.store.service.impl;

import com.reine.store.entity.Address;
import com.reine.store.mapper.AddressMapper;
import com.reine.store.service.IAddressService;
import com.reine.store.service.IDistrictService;
import com.reine.store.service.ex.*;
import com.reine.store.vo.AddressVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 地址业务类
 *
 * @author reine
 * 2022/5/7 20:09
 */
@Service
public class AddressServiceImpl implements IAddressService {

    @Autowired
    private AddressMapper addressMapper;

    @Autowired
    private IDistrictService districtService;

    /**
     * 用户地址最大条数
     */
    @Value("${user.address.max-count}")
    private Integer maxCount;

    /**
     * 添加收货地址
     *
     * @param uid      用户uid
     * @param username 用户名
     * @param address  用户输入的地址信息
     */
    @Override
    public void addNewAddress(Integer uid, String username, Address address) {
        // 查询用户收货地址是否已达上限
        Integer count = addressMapper.countByUid(uid);
        if (count >= maxCount) {
            throw new AddressCountLimitException("用户收货地址超出上限");
        }
        address = fillProperties(address, uid, username);
        Integer rows = addressMapper.insert(address);
        if (rows != 1) {
            throw new InsertException("添加用户地址时产生未知异常");
        }

    }

    /**
     * 根据用户uid获取地址信息
     *
     * @param uid 用户uid
     * @return 地址信息
     */
    @Override
    public List<AddressVo> getByUid(Integer uid) {
        List<Address> addresses = addressMapper.findByUid(uid);
        List<AddressVo> addressVos = addresses.stream().map(address -> {
            AddressVo addressVo = new AddressVo();
            BeanUtils.copyProperties(address, addressVo);
            return addressVo;
        }).collect(Collectors.toList());
        return addressVos;
    }

    /**
     * 设置默认地址
     *
     * @param aid      地址aid
     * @param uid      用户uid
     * @param username 用户名
     */
    @Override
    public void setDefault(Integer aid, Integer uid, String username) {
        Address result = addressMapper.findByAid(aid);
        if (result == null) {
            throw new AddressNotFoundException("收货地址不存在");
        }
        // 检测数据归属
        if (!result.getUid().equals(uid)) {
            throw new AccessDeniedException("非法访问");
        }
        // 更新数据
        Integer rows = addressMapper.updateNonDefault(uid);
        if (rows < 1) {
            throw new UpdateException("更新数据产生未知异常");
        }
        // 将用户选中的某条地址设置为默认收货地址
        Address address = new Address();
        address.setAid(aid);
        address.setIsDefault(1);
        address.setModifiedUser(username);
        address.setModifiedTime(LocalDateTime.now());
        rows = addressMapper.updateByAid(address);
        if (rows != 1) {
            throw new UpdateException("更新数据产生未知异常");
        }
    }

    /**
     * 根据aid获取地址详细数据
     *
     * @param aid 地址aid
     * @return 地址详细数据
     */
    @Override
    public Address getAddressDetail(Integer aid, Integer uid) {
        Address address = addressMapper.findByAid(aid);
        if (address == null) {
            throw new AddressNotFoundException("未找到地址");
        }
        if (!address.getUid().equals(uid)) {
            throw new AccessDeniedException("非法访问");
        }
        return address;
    }

    /**
     * 删除用户选中的地址
     *
     * @param aid      地址aid
     * @param uid      用户uid
     * @param username 用户名
     */
    @Override
    public void delete(Integer aid, Integer uid, String username) {
        Address result = addressMapper.findByAid(aid);
        if (result == null) {
            throw new AddressNotFoundException("收货地址不存在");
        }
        // 检测数据归属
        if (!result.getUid().equals(uid)) {
            throw new AccessDeniedException("非法访问");
        }
        Integer rows = addressMapper.deleteByAid(aid);
        if (rows != 1) {
            throw new DeleteException("删除地址是产生未知错误");
        }
        Integer count = addressMapper.countByUid(uid);
        // 用户地址已全部清空
        if (count == 0) {
            return;
        }
        // 删除的是默认地址
        if (result.getIsDefault() == 1) {
            Address address = addressMapper.findLastModified(uid);
            address.setIsDefault(1);
            address.setModifiedUser(username);
            address.setModifiedTime(LocalDateTime.now());
            rows = addressMapper.updateByAid(address);
        }

        if (rows != 1) {
            throw new UpdateException("更新地址时产生未知错误");
        }
    }

    /**
     * 编辑收货地址
     *
     * @param uid      用户uid
     * @param username 用户名
     * @param address  收货地址
     */
    @Override
    public void editAddress(Integer uid, String username, Address address) {
        Integer aid = address.getAid();
        Address result = addressMapper.findByAid(aid);
        if (result == null) {
            throw new AddressNotFoundException("收货地址不存在");
        }
        // 检测数据归属
        if (!result.getUid().equals(uid)) {
            throw new AccessDeniedException("非法访问");
        }
        fillProperties(address, uid, username);
        Integer rows = addressMapper.updateByAid(address);
        if (rows != 1) {
            throw new UpdateException("更新地址时产生未知错误");
        }
    }

    /**
     * 地址字段填充
     *
     * @param address  地址
     * @param uid      用户uid
     * @param username 用户名
     * @return 填充完成的地址
     */
    private Address fillProperties(Address address, Integer uid, String username) {
        Integer count = addressMapper.countByUid(uid);
        // 补全省市区数据
        String provinceName = districtService.getNameByCode(address.getProvinceCode());
        String cityName = districtService.getNameByCode(address.getCityCode());
        String areaName = districtService.getNameByCode(address.getAreaCode());
        address.setProvinceName(provinceName);
        address.setCityName(cityName);
        address.setAreaName(areaName);
        address.setUid(uid);
        Integer isDefault = count == 0 ? 1 : 0;
        address.setIsDefault(isDefault);
        address.setCreatedUser(username);
        address.setModifiedUser(username);
        LocalDateTime now = LocalDateTime.now();
        address.setCreatedTime(now);
        address.setModifiedTime(now);
        return address;
    }
}
