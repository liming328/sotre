package com.yaorange.store.service.impl;

import com.yaorange.store.dao.CategoryDao;
import com.yaorange.store.orm.Category;
import com.yaorange.store.service.CategoryService;
import com.yaorange.store.utils.MybatisUtil;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class CategoryServiceImpl implements CategoryService {
    @Override
    public List<Category> findAll() {
        SqlSession sqlSession = null;
        try {
            sqlSession = MybatisUtil.getSqlSession();
            CategoryDao categoryDao = sqlSession.getMapper(CategoryDao.class);
            List<Category> categoryList = categoryDao.findAll();
            return categoryList;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }finally {
            MybatisUtil.closeSqlSession(sqlSession);
        }
    }
}
