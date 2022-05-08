package com.reine.store.mapper;

import com.reine.store.entity.Address;

import java.util.List;

/**
 * 地址模块持久层接口
 *
 * @author reine
 * 2022/5/7 20:08
 */
public interface AddressMapper {

    /**
     * 插入用户的收货地址
     *
     * @param address 收货地址
     * @return 受影响的行数
     */
    Integer insert(Address address);

    /**
     * 根据用户uid查询收货地址数量
     *
     * @param uid 用户uid
     * @return 当前用户的收货地址总数
     */
    Integer countByUid(Integer uid);

    /**
     * 根据用户uid查询用户收货地址数据
     * @param uid 用户uid
     * @return 用户地址数据
     */
    List<Address> findByUid(Integer uid);

    /**
     * 根据aid查询收货地址数据
     * @param aid 收货地址aid
     * @return 收货地址数据
     */
    Address findByAid(Integer aid);

    /**
     * 根据用户uid修改用户收货地址设置为非默认
     * @param uid 用户uid
     * @return 受影响的行数
     */
    Integer updateNonDefault(Integer uid);

    /**
     * 根据地址aid设置更新用户默认地址
     * @param address 地址
     * @return 受影响的行数
     */
    Integer updateByAid(Address address);

    /**
     * 根据aid删除地址
     * @param aid 地址aid
     * @return 受影响的行数
     */
    Integer deleteByAid(Integer aid);

    /**
     * 根据用户uid查询最后一条被修改的地址
     * @param uid 用户uid
     * @return 最后一条修改的数据
     */
    Address findLastModified(Integer uid);
}
