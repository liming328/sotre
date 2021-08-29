package com.yaorange.store.dao;

import com.yaorange.store.entity.Order;

import java.util.List;

public interface OrderDao {
    /**
     * 保存订单
     * @param order
     * @throws Exception
     */
    void save(Order order)throws Exception;

    /**
     * 查询某用户的订单总数
     * @param uid
     * @return
     * @throws Exception
     */
    Integer getTotalCountByUid(String uid) throws Exception;

    /**
     * 分页查询某用户的订单
     * @param uid
     * @param beginRowIndex
     * @param pageSize
     * @return
     */
    List<Order> findListByUid(String uid, Integer beginRowIndex, Integer pageSize)throws Exception;

    /**
     * 修改订单状态 1-已 0-未
     * @param oid
     * @param newState
     * @throws Exception
     */
    void updateState(String oid, Integer newState)throws Exception;
}
