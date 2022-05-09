package com.reine.store.service.impl;

import com.reine.store.entity.Address;
import com.reine.store.entity.Order;
import com.reine.store.entity.OrderItem;
import com.reine.store.mapper.OrderMapper;
import com.reine.store.service.IAddressService;
import com.reine.store.service.ICartService;
import com.reine.store.service.IOrderService;
import com.reine.store.service.ex.InsertException;
import com.reine.store.vo.CartVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 处理订单和订单数据的业务层实现类
 */
@Transactional
@Service
public class OrderServiceImpl implements IOrderService {
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private IAddressService addressService;
    @Autowired
    private ICartService cartService;

    /**
     * 创建订单
     *
     * @param aid      收货地址的id
     * @param cids     即将购买的商品数据在购物车表中的id
     * @param uid      当前登录的用户的id
     * @param username 当前登录的用户名
     * @return 成功创建的订单数据
     */
    @Override
    public Order create(Integer aid, Integer[] cids, Integer uid, String username) {
        // 获取商品信息
        List<CartVo> list = cartService.getVOByCids(uid, cids);
        // 获取订单总价
        Long totalPrice = 0L;
        for (CartVo cartVo : list) {
            totalPrice += cartVo.getPrice() * cartVo.getNum();
        }
        Address address = addressService.getAddressDetail(aid, uid);
        Order order = new Order();
        // 补全数据：uid
        order.setUid(uid);
        // 查询收货地址数据
        // 补全数据：收货地址相关的6项
        order.setRecvName(address.getName());
        order.setRecvPhone(address.getPhone());
        order.setRecvProvince(address.getProvinceName());
        order.setRecvCity(address.getCityName());
        order.setRecvArea(address.getAreaName());
        order.setRecvAddress(address.getAddress());
        // 补全数据：totalPrice
        order.setTotalPrice(totalPrice);
        // 补全数据：status
        order.setStatus(0);
        // 补全数据：下单时间
        LocalDateTime now = LocalDateTime.now();
        order.setOrderTime(now);
        // 补全数据：日志
        order.setCreatedUser(username);
        order.setModifiedUser(username);
        order.setCreatedTime(now);
        order.setModifiedTime(now);
        Integer rows = orderMapper.insertOrder(order);
        if (rows != 1) {
            throw new InsertException("插入异常");
        }
        // 创建订单项
        list.forEach(item -> {
            OrderItem orderItem = new OrderItem();
            // 由于mapper层sql插入语句配置了keyProperty="oid" useGeneratedKeys="true"，故此处可以获取到oid
            orderItem.setOid(order.getOid());
            BeanUtils.copyProperties(item, orderItem);
            orderItem.setCreatedUser(username);
            orderItem.setModifiedUser(username);
            orderItem.setCreatedTime(now);
            orderItem.setModifiedTime(now);
            Integer row = orderMapper.insertOrderItem(orderItem);
            // 插入数据
            if (row != 1) {
                throw new InsertException("插入数据异常");
            }
        });
        return order;
    }

}