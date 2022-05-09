package com.reine.store.service;

import com.reine.store.vo.CartVo;

import java.util.List;

/**
 * @author reine
 * 2022/5/9 10:20
 */
public interface ICartService {
    /**
     * 将商品添加到购物车
     *
     * @param uid      当前登录用户的id
     * @param pid      商品的id
     * @param amount   增加的数量
     * @param username 当前登录的用户名
     */
    void addToCart(Integer uid, Integer pid, Integer amount, String username);

    /**
     * 查询某用户的购物车数据
     *
     * @param uid 用户id
     * @return 该用户的购物车数据的列表
     */
    List<CartVo> getVOByUid(Integer uid);

    /**
     * 将购物车中某商品的数量变更
     *
     * @param cid      购物车数量的id
     * @param uid      当前登录的用户的id
     * @param num      变更的数量
     * @param username 当前登录的用户名
     * @return 增加成功后新的数量
     */
    Integer changeNum(Integer cid, Integer uid, Integer num, String username);

    /**
     * 根据若干个购物车数据id查询详情的列表
     * @param uid 当前登录的用户的id
     * @param cids 若干个购物车数据id
     * @return 匹配的购物车数据详情的列表
     */
    List<CartVo> getVOByCids(Integer uid, Integer[] cids);

    /**
     * 根据商品cid删除商品
     * @param cid 购物车商品cid
     * @param uid 用户uid
     */
    void removeByCid(Integer cid, Integer uid);

    /**
     * 删除选中的商品
     * @param ids 选中的商品cid
     * @param uid 用户uid
     */
    void removeCheck(Integer[] ids, Integer uid);
}
