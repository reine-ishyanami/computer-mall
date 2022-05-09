package com.reine.store.controller;

import com.reine.store.service.ICartService;
import com.reine.store.vo.CartVo;
import com.reine.store.vo.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("carts")
public class CartController extends BaseController {
    @Autowired
    private ICartService cartService;

    /**
     * 添加商品到购物车
     *
     * @param pid     商品pid
     * @param amount  商品数量
     * @param session session对象
     * @return 返回信息
     */
    @RequestMapping("add_to_cart")
    public JsonResult<Void> addToCart(Integer pid, Integer amount, HttpSession session) {
        // 从Session中获取uid和username
        Integer uid = getUidFromSession(session);
        String username = getUsernameFromSession(session);
        // 调用业务对象执行添加到购物车
        cartService.addToCart(uid, pid, amount, username);
        // 返回成功
        return new JsonResult<>(OK);
    }

    /**
     * 查询购物车列表
     *
     * @param session session对象
     * @return 返回信息
     */
    @RequestMapping
    public JsonResult<List<CartVo>> getVOByUid(HttpSession session) {
        // 从Session中获取uid
        Integer uid = getUidFromSession(session);
        // 调用业务对象执行查询数据
        List<CartVo> data = cartService.getVOByUid(uid);
        // 返回成功与数据
        return new JsonResult<>(OK, data);
    }

    /**
     * 改变购物车中商品数量
     *
     * @param cid     购物车cid
     * @param num     变更的数量
     * @param session session对象
     * @return 返回信息
     */
    @RequestMapping("{cid}/{num}")
    public JsonResult<Integer> changeNum(@PathVariable("cid") Integer cid, @PathVariable("num") Integer num, HttpSession session) {
        // 从Session中获取uid和username
        Integer uid = getUidFromSession(session);
        String username = getUsernameFromSession(session);
        // 调用业务对象执行增加数量
        Integer data = cartService.changeNum(cid, uid, num, username);
        // 返回成功
        return new JsonResult<>(OK, data);
    }

    /**
     * 获取购物车指定cid的商品列表
     *
     * @param cids    商品cid数组
     * @param session session对象
     * @return 返回信息
     */
    @RequestMapping("list")
    public JsonResult<List<CartVo>> getVOByCids(Integer[] cids, HttpSession session) {
        // 从Session中获取uid
        Integer uid = getUidFromSession(session);
        // 调用业务对象执行查询数据
        List<CartVo> data = cartService.getVOByCids(uid, cids);
        // 返回成功与数据
        return new JsonResult<>(OK, data);
    }

    /**
     * 根据id删除商品
     *
     * @param cid     商品cid
     * @param session session对象
     * @return 返回信息
     */
    @RequestMapping("{cid}/remove")
    public JsonResult<Void> removeCart(@PathVariable("cid") Integer cid, HttpSession session) {
        Integer uid = getUidFromSession(session);
        cartService.removeByCid(cid, uid);
        return new JsonResult<>(OK);
    }

    /**
     * 删除选中
     * @param ids 选中的id
     * @param session session对象
     * @return 返回信息
     */
    @RequestMapping("removeCheck")
    public JsonResult<Void> removeCheck(Integer[] ids, HttpSession session) {
        Integer uid = getUidFromSession(session);
        cartService.removeCheck(ids, uid);
        return new JsonResult<>(OK);
    }
}