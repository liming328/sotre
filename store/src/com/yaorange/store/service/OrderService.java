package com.yaorange.store.service;

import com.yaorange.store.entity.Order;
import com.yaorange.store.entity.OrderItem;
import com.yaorange.store.entity.Page;

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

    /**
     * 根据用户查询订单详情
     * @param uid
     * @param currPage
     * @return
     * @throws Exception
     */
    Page findPageByUid(String uid, String currPage) throws Exception;
    /**
     * 修改订单状态 1-已 0-未
     * @param oid
     * @param newState
     * @throws Exception
     */
    void updateState(String oid, Integer newState)throws Exception;
}
