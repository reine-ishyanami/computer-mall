package com.reine.store.service;

import com.reine.store.vo.ProductVo;

import java.util.List;

/**
 * @author reine
 * 2022/5/9 7:51
 */
public interface IProductService {

    /**
     * 查询热门商品列表
     *
     * @return 热门商品列表
     */
    List<ProductVo> findHotList();

    /**
     * 查询商品详情
     *
     * @param id 商品id
     * @return 商品详情
     */
    ProductVo findById(Integer id);
}
