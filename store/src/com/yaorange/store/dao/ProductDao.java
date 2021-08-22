package com.yaorange.store.dao;

import com.yaorange.store.entity.Category;
import com.yaorange.store.entity.Product;

import java.util.List;

public interface ProductDao {
    /**
     * 日期查找热门商品集合
     * @return
     */
    public List<Product> findHotProductList();

    /**
     * 查询某类别某页的商品
     * @param cid
     * @param beginRowIndex
     * @param pageSize
     * @return
     */
    public List<Product> findPageByCid(String cid, Integer beginRowIndex, Integer pageSize);

    /**
     * 查询某类别商品上架总数
     * @param cid
     * @param pflag
     * @return
     * @throws Exception
     */
    public int getTotalCount(String cid, Integer pflag) throws Exception;

    /**
     * 根据商品id查询商品
     * @param pid
     * @return
     * @throws Exception
     */
    public Product findByPid(String pid)throws Exception;

    /**
     * 根据cid查分类
     * @param cid
     * @return
     */
    public Category findByCid(String cid)throws Exception;

}
