package com.yaorange.store.service;

import com.yaorange.store.entity.Order;
import com.yaorange.store.entity.OrderItem;

import java.util.List;

/**
 * 订单业务
 */
public interface OrderService {
    /**
     * 下单
     * @param order
     * @param orderItemList
     * @throws Exception
     */
    void save(Order order, List<OrderItem> orderItemList) throws Exception;
}
