package com.reine.store.entity;

import lombok.Data;

/**
 * @author reine
 * 2022/5/9 9:31
 */
@Data
public class Cart extends BaseEntity {
    /**
     * 购物车id
     */
    private Integer cid;
    /**
     * 用户id
     */
    private Integer uid;
    /**
     * 商品id
     */
    private Integer pid;
    /**
     * 加入购物车时的价格
     */
    private Long price;
    /**
     * 数量
     */
    private Integer num;
}
