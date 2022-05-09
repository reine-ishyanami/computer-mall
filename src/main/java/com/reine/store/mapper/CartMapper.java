package com.reine.store.mapper;

import com.reine.store.entity.Cart;
import com.reine.store.vo.CartVo;

import java.util.List;

/**
 * @author reine
 * 2022/5/9 9:38
 */
public interface CartMapper {

    /**
     * 插入购物车
     * @param cart 购物车数据
     * @return 受影响的行数
     */
    Integer insert(Cart cart);

    /**
     * 更新购物车某商品数据
     * @param cart 购物车数据
     * @return 受影响的行数
     */
    Integer updateCartByCid(Cart cart);

    /**
     * 根据用户uid和商品pid查询购物车商品数据
     * @param uid 用户uid
     * @param pid 商品pid
     * @return 购物车数据
     */
    Cart findByCUidAndPid(Integer uid, Integer pid);

    /**
     * 查询某用户的购物车数据
     * @param uid 用户id
     * @return 该用户的购物车数据的列表
     */
    List<CartVo> findVOByUid(Integer uid);

    /**
     * 根据购物车数据id查询购物车数据详情
     * @param cid 购物车数据id
     * @return 匹配的购物车数据详情，如果没有匹配的数据则返回null
     */
    Cart findByCid(Integer cid);

    /**
     * 删除购物车数据
     * @param cid 购物车cid
     * @return 受影响的行数
     */
    Integer deleteByCid(Integer cid);

    /**
     * 根据若干个购物车数据id查询详情的列表
     * @param cids 若干个购物车数据id
     * @return 匹配的购物车数据详情的列表
     */
    List<CartVo> findVOByCids(Integer[] cids);

    /**
     * 根据购物车商品cid查询商品信息
     * @param cid 购物车cid
     * @return 购物车商品信息
     */
    Cart findVOByCid(Integer cid);

    /**
     * 根据购物车商品cid删除购物车商品数据
     * @param cid 购物车商品cid
     */
    Integer deleteCart(Integer cid);
}
