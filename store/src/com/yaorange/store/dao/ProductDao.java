package com.yaorange.store.dao;

import com.yaorange.store.entity.Category;
import com.yaorange.store.entity.Product;

import java.util.List;

public interface ProductDao {
    /**
     * 日期查找热门商品集合
     * @return
     */
    public List<Product> findHotProductList()throws Exception;

    /**
     * 查询某类别某页的商品
     * @param cid
     * @param beginRowIndex
     * @param pageSize
     * @return
     */
    public List<Product> findPageByCid(String cid, Integer beginRowIndex, Integer pageSize)throws Exception;

    /**
     * 查询所有商品的分页
     * @param beginRowIndex
     * @param pageSize
     * @return
     * @throws Exception
     */
    public List<Product> findPage( Integer beginRowIndex, Integer pageSize)throws Exception;

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
     * @return pflag
     * @throws Exception
     */
    public void updatePflag(String pid,Integer pflag)throws Exception;

    /**
     * 新增商品
     * @param product
     * @throws Exception
     */
    public void save(Product product)throws Exception;
}
