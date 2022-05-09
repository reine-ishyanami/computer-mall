package com.reine.store.vo;

import lombok.Data;

/**
 * @author reine
 * 2022/5/9 11:46
 */
@Data
public class CartVo {
    /**
     * 购物车cid
     */
    private Integer cid;
    /**
     * 用户uid
     */
    private Integer uid;
    /**
     * 商品pid
     */
    private Integer pid;
    /**
     * 商品在购物车中的价格
     */
    private Long price;
    /**
     * 商品数量
     */
    private Integer num;
    /**
     * 商品标题
     */
    private String title;
    /**
     * 商品现在的价格
     */
    private Long realPrice;
    /**
     * 商品图片地址
     */
    private String image;
}