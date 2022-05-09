package com.reine.store.service.impl;

import com.reine.store.entity.Cart;
import com.reine.store.mapper.CartMapper;
import com.reine.store.service.ICartService;
import com.reine.store.service.IProductService;
import com.reine.store.service.ex.*;
import com.reine.store.vo.CartVo;
import com.reine.store.vo.ProductVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author reine
 * 2022/5/9 10:24
 */
@Service
public class CartServiceImpl implements ICartService {

    @Autowired
    private CartMapper cartMapper;
    @Autowired
    private IProductService productService;

    /**
     * 将商品添加到购物车
     *
     * @param uid      当前登录用户的id
     * @param pid      商品的id
     * @param amount   增加的数量
     * @param username 当前登录的用户名
     */
    @Override
    public void addToCart(Integer uid, Integer pid, Integer amount, String username) {
        // 查询当前要添加购物车的商品是否存在于表中
        Cart result = cartMapper.findByCUidAndPid(uid, pid);
        if (result == null) {
            // 新增购物车数据
            Cart cart = new Cart();
            cart.setUid(uid);
            cart.setPid(pid);
            cart.setNum(amount);
            // 补全价格
            ProductVo product = productService.findById(pid);
            cart.setPrice(product.getPrice());
            // 补全日志
            cart.setCreatedUser(username);
            cart.setModifiedUser(username);
            LocalDateTime now = LocalDateTime.now();
            cart.setCreatedTime(now);
            cart.setModifiedTime(now);
            Integer rows = cartMapper.insert(cart);
            if (rows != 1) {
                throw new InsertException("插入数据异常");
            }
        } else {
            // 更新购物车数据
            Integer num = result.getNum() + amount;
            result.setNum(num);
            result.setModifiedUser(username);
            result.setModifiedTime(LocalDateTime.now());
            Integer rows = cartMapper.updateCartByCid(result);
            if (rows != 1) {
                throw new UpdateException("更新数据异常");
            }
        }
    }

    /**
     * 查询某用户的购物车数据
     *
     * @param uid 用户id
     * @return 该用户的购物车数据的列表
     */
    @Override
    public List<CartVo> getVOByUid(Integer uid) {
        return cartMapper.findVOByUid(uid);
    }

    /**
     * 将购物车中某商品的数量加1
     *
     * @param cid      购物车数量的id
     * @param uid      当前登录的用户的id
     * @param ch       变更的数量
     * @param username 当前登录的用户名
     * @return 增加成功后新的数量
     */
    @Override
    public Integer changeNum(Integer cid, Integer uid, Integer ch, String username) {
        Cart result = cartMapper.findByCid(cid);
        if (result == null) {
            throw new CartNotFoundException("数据不存在");
        }
        // 检测数据归属
        if (!result.getUid().equals(uid)) {
            throw new AccessDeniedException("非法访问");
        }
        Integer num = result.getNum() + ch;
        if (num == 0) {
            Integer rows = cartMapper.deleteByCid(cid);
            if (rows != 1) {
                throw new DeleteException("删除异常");
            }
            return num;
        }
        result.setNum(num);
        result.setModifiedUser(username);
        result.setModifiedTime(LocalDateTime.now());
        Integer rows = cartMapper.updateCartByCid(result);
        if (rows != 1) {
            throw new UpdateException("更新失败");
        }
        // 返回新的购物车总量
        return num;
    }

    /**
     * 根据若干个购物车数据id查询详情的列表
     *
     * @param uid  当前登录的用户的id
     * @param cids 若干个购物车数据id
     * @return 匹配的购物车数据详情的列表
     */
    @Override
    public List<CartVo> getVOByCids(Integer uid, Integer[] cids) {
        List<CartVo> list = cartMapper.findVOByCids(cids);
        // 过滤掉不属于当前用户的商品
        return list.stream().filter(cartVo -> cartVo.getUid().equals(uid)).collect(Collectors.toList());
    }

    /**
     * 根据商品cid删除商品
     *
     * @param cid 购物车商品cid
     * @param uid 用户uid
     */
    @Override
    public void removeByCid(Integer cid, Integer uid) {
        // 查询当前购物车商品数据
        Cart cart = cartMapper.findVOByCid(cid);
        if (!cart.getUid().equals(uid)) {
            throw new AccessDeniedException("非法操作");
        }
        Integer rows = cartMapper.deleteByCid(cid);
        if (rows != 1) {
            throw new DeleteException("删除异常");
        }
    }

    /**
     * 删除选中的商品
     *
     * @param ids 选中的商品cid
     * @param uid 用户uid
     */
    @Override
    public void removeCheck(Integer[] ids, Integer uid) {
        List<CartVo> cartVos = cartMapper.findVOByCids(ids);
        // 查询属于用户uid下的商品
        List<CartVo> list = cartVos.stream().filter(cartVo -> cartVo.getUid().equals(uid)).collect(Collectors.toList());
        list.forEach(item -> {
            Integer rows = cartMapper.deleteByCid(item.getCid());
            if (rows != 1) {
                throw new DeleteException("删除异常");
            }
        });
    }
}
