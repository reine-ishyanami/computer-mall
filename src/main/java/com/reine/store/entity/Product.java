package com.reine.store.entity;

import lombok.Data;

/**
 * 产品实体类
 *
 * @author reine
 * 2022/5/9 7:40
 */
@Data
public class Product extends BaseEntity {

    /**
     * 主键，商品id
     */
    private Integer id;
    /**
     * 分类id
     */
    private Integer category_id;
    /**
     * 商品系列
     */
    private String item_type;
    /**
     * 商品标题
     */
    private String title;
    /**
     * 商品卖点
     */
    private String sell_point;
    /**
     * 商品单价
     */
    private Long price;
    /**
     * 库存数量
     */
    private Integer num;
    /**
     * 图片路径
     */
    private String image;
    /**
     * 商品状态  1：上架   2：下架   3：删除
     */
    private Integer status;
    /**
     * 显示优先级
     */
    private Integer priority;
}
