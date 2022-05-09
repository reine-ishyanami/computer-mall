package com.reine.store.entity;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * 订单数据的实体类
 *
 * @author reine
 * 2022/5/9 19:20
 */
@Data
public class Order extends BaseEntity {
    /**
     * 订单oid
     */
    private Integer oid;
    /**
     * 用户uid
     */
    private Integer uid;
    /**
     * 收货人姓名
     */
    private String recvName;
    /**
     * 收货人电话
     */
    private String recvPhone;
    /**
     * 收货人所在省
     */
    private String recvProvince;
    /**
     * 收货人所在市
     */
    private String recvCity;
    /**
     * 收货人所在区
     */
    private String recvArea;
    /**
     * 收货详细地址
     */
    private String recvAddress;
    /**
     * 总价
     */
    private Long totalPrice;
    /**
     * 状态：0-未支付，1-已支付，2-已取消，3-已关闭，4-已完成
     */
    private Integer status;
    /**
     * 下单时间
     */
    private LocalDateTime orderTime;
    /**
     * 支付时间
     */
    private LocalDateTime payTime;

}    