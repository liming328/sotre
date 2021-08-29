package com.yaorange.store.test;

import com.yaorange.store.entity.Order;
import com.yaorange.store.entity.OrderItem;
import com.yaorange.store.entity.Page;
import com.yaorange.store.entity.Product;
import com.yaorange.store.service.OrderService;
import com.yaorange.store.service.impl.OrderServiceImpl;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class OrderServiceTest {
    private OrderService orderService;
    @Before
    public void setUp()throws Exception {
        if (null==orderService){
            orderService = new OrderServiceImpl();
        }
    }
    @Test
    public void testFindPageByUid()throws Exception{
        String uid = "f55b7d3a352a4f0782c910b2c70f1ea4";
        String currPage = "1";
        Page page = orderService.findPageByUid(uid, currPage);
        System.out.println(page.getPageSize());
        System.out.println(page.getCurrPage());
        System.out.println(page.getTotalCount());
        System.out.println(page.getTotalPage());
        List<Order> list = page.getList();
        for (Order order : list) {
            List<OrderItem> orderItemList = order.getOrderItemList();
            for (OrderItem orderItem : orderItemList) {
                System.out.println(orderItem.getCount()+"___"+orderItem.getSubtotal());
                System.out.println("____________________________");
                Product product = orderItem.getProduct();
                System.out.println(product.getPname()+"__"+product.getPimage()+product.getShop_price());
            }
        }
    }
}
