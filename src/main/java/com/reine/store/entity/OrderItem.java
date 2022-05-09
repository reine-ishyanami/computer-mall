package com.reine.store.entity;

import lombok.Data;

/**
 * 订单中的商品记录实体类
 *
 * @author reine
 * 2022/5/9 19:15
 */
@Data
public class OrderItem extends BaseEntity {
    /**
     * 订单中的商品记录的id
     */
    private Integer id;
    /**
     * 所归属的订单的id
     */
    private Integer oid;
    /**
     * 商品的id
     */
    private Integer pid;
    /**
     * 商品标题
     */
    private String title;
    /**
     * 商品图片
     */
    private String image;
    /**
     * 商品价格
     */
    private Long price;
    /**
     * 购买数量
     */
    private Integer num;
}
