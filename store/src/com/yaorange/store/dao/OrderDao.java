package com.yaorange.store.dao;

import com.yaorange.store.entity.Order;

public interface OrderDao {
    /**
     * 保存订单
     * @param order
     * @throws Exception
     */
    void save(Order order)throws Exception;
}
