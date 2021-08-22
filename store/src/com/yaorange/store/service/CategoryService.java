package com.yaorange.store.service;

import com.yaorange.store.entity.Category;

import java.util.List;

public interface CategoryService {
    /**
     * 查询所有种类
     * @return
     */
    public List<Category> findAll();
}
