package com.yaorange.store.service.impl;

import com.yaorange.store.dao.OrderDao;
import com.yaorange.store.dao.OrderItemDao;
import com.yaorange.store.entity.Order;
import com.yaorange.store.entity.OrderItem;
import com.yaorange.store.entity.Page;
import com.yaorange.store.service.OrderService;
import com.yaorange.store.utils.MybatisUtil;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class OrderServiceImpl implements OrderService {
    @Override
    public void save(Order order, List<OrderItem> orderItemList) throws Exception {
        SqlSession sqlSession = null;
        try {
            sqlSession = MybatisUtil.getSqlSession();
            OrderDao orderDao = sqlSession.getMapper(OrderDao.class);
            //保存订单
            orderDao.save(order);
            //保存订单明细
            OrderItemDao orderItemDao = sqlSession.getMapper(OrderItemDao.class);
            for (OrderItem orderItem : orderItemList) {
                orderItemDao.save(orderItem);
            }
            //一个事务
            sqlSession.commit();
        } catch (Exception e) {
            sqlSession.rollback();
        }finally {
            MybatisUtil.closeSqlSession(sqlSession);
        }



    }

    @Override
    public Page findPageByUid(String uid, String currPage) throws Exception {
        SqlSession sqlSession = MybatisUtil.getSqlSession();
        OrderDao orderDao = sqlSession.getMapper(OrderDao.class);
        if(null==currPage || currPage.isEmpty()){
            currPage = "1";
        }
        //产生的订单分页详情
        Page page = new Page();
        page.setCurrPage(Integer.valueOf(currPage));
        page.setTotalCount(orderDao.getTotalCountByUid(uid));
        List<Order> orderList = orderDao.findListByUid(uid, (page.getCurrPage()-1)*page.getPageSize(), page.getPageSize());
        page.setList(orderList);
        MybatisUtil.closeSqlSession(sqlSession);
        return page;
    }

    @Override
    public void updateState(String oid, Integer newState) throws Exception {
        SqlSession sqlSession = null;
        try {
            sqlSession = MybatisUtil.getSqlSession();
            OrderDao orderDao = sqlSession.getMapper(OrderDao.class);
            orderDao.updateState(oid,newState);
            sqlSession.commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (null != sqlSession) {
                sqlSession.rollback();
            }
        }finally {
            MybatisUtil.closeSqlSession(sqlSession);
        }
    }

}
