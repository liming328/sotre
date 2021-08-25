package com.yaorange.store.service;

import com.yaorange.store.entity.OrderItem;

public interface OrderItemService {
    /**
     * 订单中添加一个订单明细
     * @param orderItem
     * @throws Exception
     */
    void save(OrderItem orderItem)throws Exception;
}
