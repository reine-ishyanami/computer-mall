package com.reine.store.service;

import com.reine.store.entity.Address;
import com.reine.store.vo.AddressVo;

import java.util.List;

/**
 * @author reine
 * 2022/5/7 20:09
 */
public interface IAddressService {
    /**
     * 添加收货地址
     *
     * @param uid      用户uid
     * @param username 用户名
     * @param address  用户输入的地址信息
     */
    void addNewAddress(Integer uid, String username, Address address);

    /**
     * 根据用户uid获取地址信息
     *
     * @param uid 用户uid
     * @return 地址信息
     */
    List<AddressVo> getByUid(Integer uid);

    /**
     * 设置默认地址
     *
     * @param aid      地址id
     * @param uid      用户id
     * @param username 用户名
     */
    void setDefault(Integer aid, Integer uid, String username);

    /**
     * 根据aid获取地址详细数据
     *
     * @param aid 地址aid
     * @param uid 用户uid
     * @return 地址详细数据
     */
    Address getAddressDetail(Integer aid, Integer uid);

    /**
     * 删除用户选中的地址
     *
     * @param aid      地址aid
     * @param uid      用户uid
     * @param username 用户名
     */
    void delete(Integer aid, Integer uid, String username);

    /**
     * 编辑收货地址
     *
     * @param uid      用户uid
     * @param username 用户名
     * @param address  收货地址
     */
    void editAddress(Integer uid, String username, Address address);
}
