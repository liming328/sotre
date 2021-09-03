package com.yaorange.store.service;

import com.yaorange.store.entity.Category;
import com.yaorange.store.entity.Page;
import com.yaorange.store.entity.Product;

import java.util.List;

public interface ProductService {
    /**
     * 查找热门商品集合
     * @return
     */
    public List<Product> findHotProductList() throws Exception;

    /**
     * 查询某类商品的分页数据
     * @param cid
     * @param currPage
     * @return
     * @throws Exception
     */
    public Page findPageByCid(String cid, String currPage)throws Exception;

    /**
     *  查询商品的分页数据
     * @param currPage
     * @return
     * @throws Exception
     */
    public Page findPage(String currPage )throws Exception;

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

    /**
     * 修改商品在售状态
     * @param pid
     * @param pfalg
     * @throws Exception
     */
    public void updatePfalg(String pid,Integer pfalg)throws Exception;

    /**
     * 新增商品
     * @param product
     * @throws Exception
     */
    public void save(Product product)throws Exception;
}
