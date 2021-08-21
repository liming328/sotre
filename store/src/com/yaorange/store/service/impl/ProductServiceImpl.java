package com.yaorange.store.service.impl;

import com.yaorange.store.dao.ProductDao;
import com.yaorange.store.orm.Product;
import com.yaorange.store.service.ProductService;
import com.yaorange.store.utils.MybatisUtil;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class ProductServiceImpl implements ProductService {
    @Override
    public List<Product> findHotProductList() throws Exception {
        SqlSession sqlSession = MybatisUtil.getSqlSession();
        ProductDao productDao = sqlSession.getMapper(ProductDao.class);
        List<Product> hotProductList = productDao.findHotProductList();
        MybatisUtil.closeSqlSession(sqlSession);
        return hotProductList;
    }
}
