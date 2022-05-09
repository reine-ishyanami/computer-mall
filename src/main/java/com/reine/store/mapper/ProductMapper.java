package com.reine.store.mapper;

import com.reine.store.entity.Product;

import java.util.List;

/**
 * @author reine
 * 2022/5/9 7:47
 */
public interface ProductMapper {

    /**
     * 查询热门商品
     * @return 热门商品列表
     */
    List<Product> findHotProduct();

    /**
     * 根据商品id查询商品详情
     * @param id 商品id
     * @return 商品详情
     */
    Product findById(Integer id);
}
