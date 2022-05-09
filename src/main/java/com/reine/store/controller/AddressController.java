package com.reine.store.controller;

import com.reine.store.entity.Address;
import com.reine.store.service.IAddressService;
import com.reine.store.vo.AddressVo;
import com.reine.store.vo.JsonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * 地址控制器
 *
 * @author reine
 * 2022/5/7 20:49
 */
@Slf4j
@RestController
@RequestMapping("addresses")
public class AddressController extends BaseController {

    @Autowired
    private IAddressService addressService;

    /**
     * 新增收货地址
     *
     * @param address 收货地址信息
     * @param session session对象
     * @return 返回信息
     */
    @RequestMapping("add_new_address")
    public JsonResult<Void> addNewAddress(Address address, HttpSession session) {
        Integer uid = getUidFromSession(session);
        String username = getUsernameFromSession(session);
        addressService.addNewAddress(uid, username, address);
        return new JsonResult<>(OK);
    }

    @RequestMapping("edit_address")
    public JsonResult<Void> editAddress(Address address, HttpSession session) {
        Integer uid = getUidFromSession(session);
        String username = getUsernameFromSession(session);
        addressService.editAddress(uid, username, address);
        return new JsonResult<>(OK);
    }

    /**
     * 获取当前用户所有收货地址
     *
     * @param session session对象
     * @return 返回信息
     */
    @RequestMapping
    public JsonResult<List<AddressVo>> getByUid(HttpSession session) {
        Integer uid = getUidFromSession(session);
        List<AddressVo> list = addressService.getByUid(uid);
        return new JsonResult<>(OK, list);
    }

    /**
     * 设置默认地址
     *
     * @param aid     地址aid
     * @param session session对象
     * @return 返回信息
     */
    @RequestMapping("{aid}/set_default")
    public JsonResult<Void> setDefault(@PathVariable("aid") Integer aid, HttpSession session) {
        Integer uid = getUidFromSession(session);
        String username = getUsernameFromSession(session);
        addressService.setDefault(aid, uid, username);
        return new JsonResult<>(OK);
    }

    /**
     * 根据aid获取地址详细数据
     *
     * @param aid 地址aid
     * @return 返回信息
     */
    @RequestMapping("detail")
    public JsonResult<Address> getAddressDetail(Integer aid) {
        Address address = addressService.getAddressDetail(aid);
        return new JsonResult<>(OK, address);
    }

    /**
     * 删除地址
     * @param aid 地址aid
     * @param session session对象
     * @return 返回信息
     */
    @RequestMapping("{aid}/delete")
    public JsonResult<Void> deleteAddress(@PathVariable("aid") Integer aid, HttpSession session) {
        Integer uid = getUidFromSession(session);
        String username = getUsernameFromSession(session);
        addressService.delete(aid, uid, username);
        return new JsonResult<>(OK);
    }
}
