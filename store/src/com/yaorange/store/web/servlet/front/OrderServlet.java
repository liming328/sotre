package com.yaorange.store.web.servlet.front;

import com.yaorange.store.entity.CartItem;
import com.yaorange.store.entity.Order;
import com.yaorange.store.entity.OrderItem;
import com.yaorange.store.entity.User;
import com.yaorange.store.service.impl.OrderServiceImpl;
import com.yaorange.store.utils.UuidUtils;
import com.yaorange.store.web.servlet.BaseServlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 下单+在线支付
 */
@WebServlet("/orderServlet")
public class OrderServlet extends BaseServlet {
    private  OrderServiceImpl orderService = new OrderServiceImpl();
    public String confirmOrder(HttpServletRequest request, HttpServletResponse response) throws Exception {
        //下单
        User user = (User) request.getSession().getAttribute("user");
        //获取表单
        String name = request.getParameter("name");
        String addrss = request.getParameter("address");
        String telephone= request.getParameter("telephone");
        String txtTotal = request.getParameter("txtTotal");
        Order order = new Order();
        order.setOid(UuidUtils.getUUID());
        order.setName(name);
        order.setAddress(addrss);
        order.setTelephone(telephone);
        order.setUid(user.getUid());
        order.setTotal(Double.valueOf(txtTotal));
        order.setState(0);
        //保存订单明细
        Map<String, CartItem> cart = (Map<String, CartItem>) request.getSession().getAttribute("cart");
        List<OrderItem> orderItemList = new ArrayList<>();
        for (Map.Entry<String, CartItem> entry : cart.entrySet()) {
            CartItem cartItem = entry.getValue();
            OrderItem orderItem = new OrderItem();
            orderItem.setItemid(UuidUtils.getUUID());
            orderItem.setOid(user.getUid());
            orderItem.setPid(cartItem.getProduct().getPid());
            orderItem.setCount(cartItem.getCount());
            orderItem.setSubtotal(cartItem.getSubtotal());
            orderItemList.add(orderItem);
        }
        try {
            orderService.save(order,orderItemList);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("msg","下单失败，重新<a href='index.jsp>'购物</a>");
        }
        //下单成功清空购物车
        request.getSession().removeAttribute("cart");
        request.setAttribute("msg","下单成功，可<a href='index.jsp'>继续购物</a>");
        return "order_info";
    }
}
