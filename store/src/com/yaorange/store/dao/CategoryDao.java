package com.yaorange.store.dao;

import com.yaorange.store.entity.Category;

import java.util.List;

public interface CategoryDao {
    /**
     * 查询所有种类
     * @return
     */
    public List<Category> findAll();
}
