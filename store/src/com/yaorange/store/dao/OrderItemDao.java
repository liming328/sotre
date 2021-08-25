package com.yaorange.store.dao;


import com.yaorange.store.entity.OrderItem;

public interface OrderItemDao {
    /**
     * 往订单中添加1条订单明细
     * @param orderItem
     * @throws Exception
     */
    void save(OrderItem orderItem) throws Exception;
}
