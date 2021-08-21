package com.yaorange.store.service;

import com.yaorange.store.orm.Product;

import java.util.List;

public interface ProductService {
    /**
     * 查找热门商品集合
     * @return
     */
    public List<Product> findHotProductList() throws Exception;
}
