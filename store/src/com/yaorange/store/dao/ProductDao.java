package com.yaorange.store.dao;

import com.yaorange.store.orm.Product;

import java.util.List;

public interface ProductDao {
    /**
     * 查找热门商品集合
     * @return
     */
    public List<Product> findHotProductList();
}
