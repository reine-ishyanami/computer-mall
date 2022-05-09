package com.reine.store.service.impl;

import com.reine.store.entity.Product;
import com.reine.store.mapper.ProductMapper;
import com.reine.store.service.IProductService;
import com.reine.store.service.ex.ProductNotFoundException;
import com.reine.store.vo.ProductVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author reine
 * 2022/5/9 7:52
 */
@Service
public class ProductServiceImpl implements IProductService {

    @Autowired
    private ProductMapper productMapper;

    /**
     * 查询热门商品列表
     *
     * @return 热门商品列表
     */
    @Override
    public List<ProductVo> findHotList() {
        List<Product> hotProduct = productMapper.findHotProduct();
        return hotProduct.stream().map(product -> {
            ProductVo productVo = new ProductVo();
            BeanUtils.copyProperties(product, productVo);
            return productVo;
        }).collect(Collectors.toList());
    }

    /**
     * 查询商品详情
     *
     * @param id 商品id
     * @return 商品详情
     */
    @Override
    public ProductVo findById(Integer id) {
        Product product = productMapper.findById(id);
        if (product == null) {
            throw new ProductNotFoundException("查询的商品数据不存在");
        }
        ProductVo productVo = new ProductVo();
        BeanUtils.copyProperties(product, productVo);
        return productVo;
    }
}
