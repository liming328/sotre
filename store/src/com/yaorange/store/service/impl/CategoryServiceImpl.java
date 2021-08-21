package com.yaorange.store.service.impl;

import com.yaorange.store.dao.CategoryDao;
import com.yaorange.store.orm.Category;
import com.yaorange.store.service.CategoryService;
import com.yaorange.store.utils.CacheUtil;
import com.yaorange.store.utils.MybatisUtil;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class CategoryServiceImpl implements CategoryService {
    @Override
    public List<Category> findAll() {
        SqlSession sqlSession = null;
        try {
           List<Category> categoryList =(List<Category>) CacheUtil.get("categoryList");
            if (null==categoryList){
                sqlSession = MybatisUtil.getSqlSession();
                CategoryDao categoryDao = sqlSession.getMapper(CategoryDao.class);
                categoryList = categoryDao.findAll();
                CacheUtil.put("categoryList",categoryList);
            }
            return categoryList;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }finally {
            MybatisUtil.closeSqlSession(sqlSession);
        }
    }
}
