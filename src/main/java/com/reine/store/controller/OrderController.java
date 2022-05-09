package com.reine.store.controller;

import com.reine.store.entity.Order;
import com.reine.store.service.IOrderService;
import com.reine.store.vo.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 * @author reine
 * 2022/5/9 21:09
 */
@RestController
@RequestMapping("orders")
public class OrderController extends BaseController {

    @Autowired
    private IOrderService orderService;

    /**
     * 创建订单
     * @param aid 地址aid
     * @param cids 购物车cids
     * @param session session对象
     * @return 返回信息
     */
    @RequestMapping("create")
    public JsonResult<Order> create(Integer aid, Integer[] cids, HttpSession session) {
        Integer uid = getUidFromSession(session);
        String username = getUsernameFromSession(session);
        Order data = orderService.create(aid, cids, uid, username);
        return new JsonResult<>(OK, data);
    }
}
