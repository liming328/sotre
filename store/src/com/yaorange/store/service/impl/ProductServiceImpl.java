package com.yaorange.store.service.impl;

import com.yaorange.store.dao.ProductDao;
import com.yaorange.store.entity.Category;
import com.yaorange.store.entity.Page;
import com.yaorange.store.entity.Product;
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

    @Override
    public Page findPageByCid(String cid, String currPage) throws Exception {
        SqlSession sqlSession = MybatisUtil.getSqlSession();
        ProductDao productDao = sqlSession.getMapper(ProductDao.class);
        if (null == currPage) {
            currPage = "1";
        }
        Page page = new Page();
        page.setCurrPage(Integer.valueOf(currPage));
        int totalCount = getTotalCount(cid, null);//不区分是否上架
        page.setTotalCount(totalCount);
        Integer totalPage = page.getTotalPage();
        page.setTotalPage(totalPage);
        Integer beginRowIndex = (page.getCurrPage() - 1) * page.getPageSize();
        List<Product> productList = productDao.findPageByCid(cid,beginRowIndex,page.getPageSize());
        page.setList(productList);
        return page;
    }

    @Override
    public int getTotalCount(String cid, Integer pflag) throws Exception {
        SqlSession sqlSession = MybatisUtil.getSqlSession();
        ProductDao productDao = sqlSession.getMapper(ProductDao.class);
        int totalCount = productDao.getTotalCount(cid, pflag);
        MybatisUtil.closeSqlSession(sqlSession);
        return totalCount;
    }

    @Override
    public Product findByPid(String pid) throws Exception {
        SqlSession sqlSession = MybatisUtil.getSqlSession();
        ProductDao productDao = sqlSession.getMapper(ProductDao.class);
        Product product = productDao.findByPid(pid);
        MybatisUtil.closeSqlSession(sqlSession);
        return product;
    }

    @Override
    public Category findByCid(String cid) throws Exception {
        SqlSession sqlSession = MybatisUtil.getSqlSession();
        ProductDao productDao = sqlSession.getMapper(ProductDao.class);
        Category category = productDao.findByCid(cid);
        MybatisUtil.closeSqlSession(sqlSession);
        return category;
    }
}
