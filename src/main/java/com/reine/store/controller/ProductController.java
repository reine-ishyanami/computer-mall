package com.reine.store.controller;

import com.reine.store.service.IProductService;
import com.reine.store.vo.JsonResult;
import com.reine.store.vo.ProductVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author reine
 * 2022/5/9 7:59
 */
@RestController
@RequestMapping("products")
public class ProductController extends BaseController {

    @Autowired
    private IProductService productService;

    /**
     * 查询热门商品
     *
     * @return 热门商品列表
     */
    @RequestMapping("hot_list")
    public JsonResult<List<ProductVo>> getHotList() {
        List<ProductVo> hotList = productService.findHotList();
        return new JsonResult<>(OK, hotList);
    }

    /**
     * 查询商品详情
     *
     * @param id 商品id
     * @return 商品详情
     */
    @RequestMapping("{id}/details")
    public JsonResult<ProductVo> getById(@PathVariable("id") Integer id) {
        ProductVo productVo = productService.findById(id);
        return new JsonResult<>(OK, productVo);
    }

}
