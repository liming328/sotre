package com.yaorange.store.web.servlet.front;

import com.yaorange.store.entity.*;
import com.yaorange.store.service.impl.OrderServiceImpl;
import com.yaorange.store.utils.PaymentUtil;
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
            orderItem.setOid(order.getOid());
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
        /*
          //在线支付
        String p0_Cmd = "Buy";
        String p1_MerId = "10001126856";// 真实
        String p2_Order = order.getOid();
        String p3_Amt = "0.01";
        String p4_Cur = "CNY";
        String p5_Pid = "";
        String p6_Pcat = "";
        String p7_Pdesc = "";
        String p8_Url = "http://localhost:80/store/orderServlet?method=callback";
        String p9_SAF = "";
        String pa_MP = "";
        String pr_NeedResponse = "";
        String pd_FrpId= request.getParameter("pd_FrpId");
        String keyValue = "69cl522AV6q613Ii4W6u8K6XuW8vM1N6bFgyv769220IuYe9u37N4y7rI4Pl";

        String hmac = PaymentUtil.buildHmac(p0_Cmd, p1_MerId, p2_Order, p3_Amt, p4_Cur,
                p5_Pid, p6_Pcat, p7_Pdesc, p8_Url, p9_SAF, pa_MP, pd_FrpId,
                pr_NeedResponse, keyValue);

        // 将所有参数 发送给 易宝指定URL
       request.setAttribute("pd_FrpId", pd_FrpId);
        request.setAttribute("p0_Cmd", p0_Cmd);
        request.setAttribute("p1_MerId", p1_MerId);
        request.setAttribute("p2_Order", p2_Order);
        request.setAttribute("p3_Amt", p3_Amt);
        request.setAttribute("p4_Cur", p4_Cur);
        request.setAttribute("p5_Pid", p5_Pid);
        request.setAttribute("p6_Pcat", p6_Pcat);
        request.setAttribute("p7_Pdesc", p7_Pdesc);
        request.setAttribute("p8_Url", p8_Url);
        request.setAttribute("p9_SAF", p9_SAF);
        request.setAttribute("pa_MP", pa_MP);
        request.setAttribute("pr_NeedResponse", pr_NeedResponse);
        request.setAttribute("hmac", hmac);
        StringBuilder url = new StringBuilder("https://www.yeepay.com/app-merchant-proxy/node?");
        url.append("p0_Cmd=").append(p0_Cmd);
        url.append("p1_MerId=").append(p1_MerId);
        url.append("p2_Order=").append(p2_Order);
        url.append("p3_Amt=").append(p3_Amt);
        url.append("p4_Cur=").append(p4_Cur);
        url.append("p5_Pid=").append(p5_Pid);
        url.append("p6_Pcat=").append(p6_Pcat);
        url.append("p7_Pdesc=").append(p7_Pdesc);
        url.append("p8_Url=").append(p8_Url);
        url.append("p9_SAF=").append(p9_SAF);
        url.append("pa_MP=").append(pa_MP);
        url.append("pr_NeedResponse=").append(pr_NeedResponse);
        url.append("pd_FrpId=").append(pd_FrpId);
        url.append("hmac=").append(hmac);
        return "redirect:"+url.toString();
         */
    }
    public String callback(HttpServletRequest request, HttpServletResponse response) throws Exception {
     // 验证请求来源和数据有效性
        // 阅读支付结果参数说明
        String p1_MerId = request.getParameter("p1_MerId");
        String r0_Cmd = request.getParameter("r0_Cmd");
        String r1_Code = request.getParameter("r1_Code");
        String r2_TrxId = request.getParameter("r2_TrxId");
        String r3_Amt = request.getParameter("r3_Amt");
        String r4_Cur = request.getParameter("r4_Cur");
        String r5_Pid = request.getParameter("r5_Pid");
        String r6_Order = request.getParameter("r6_Order");
        String r7_Uid = request.getParameter("r7_Uid");
        String r8_MP = request.getParameter("r8_MP");
        String r9_BType = request.getParameter("r9_BType");
        String rb_BankId = request.getParameter("rb_BankId");
        String ro_BankOrderId = request.getParameter("ro_BankOrderId");
        String rp_PayDate = request.getParameter("rp_PayDate");
        String rq_CardNo = request.getParameter("rq_CardNo");
        String ru_Trxtime = request.getParameter("ru_Trxtime");

        // hmac
        String hmac = request.getParameter("hmac");
        // 利用本地密钥和加密算法 加密数据
        String keyValue = "69cl522AV6q613Ii4W6u8K6XuW8vM1N6bFgyv769220IuYe9u37N4y7rI4Pl";
        boolean isValid = PaymentUtil.verifyCallback(hmac, p1_MerId, r0_Cmd,
                r1_Code, r2_TrxId, r3_Amt, r4_Cur, r5_Pid, r6_Order, r7_Uid,
                r8_MP, r9_BType, keyValue);
        if (isValid) {
            // 有效
            try {
                if (r9_BType.equals("1")) {
                    // 浏览器重定向
                    orderService.updateState(r6_Order, 1);
                    request.setAttribute("msg","支付成功");
                    /*response.setContentType("text/html;charset=utf-8");
                    response.getWriter().println(
                            "支付成功！订单号：" + r6_Order + "金额：" + r3_Amt);*/
                } else if (r9_BType.equals("2")) {
                    // 修改订单状态:
                    orderService.updateState(r6_Order, 1);
                    // 服务器点对点，来自于易宝的通知
                    //System.out.println("收到易宝通知，修改订单状态！");//
                    // 回复给易宝success，如果不回复，易宝会一直通知
                    response.getWriter().print("success");
                }else {
                    request.setAttribute("msg","支付服务后台有误");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        } else {
            throw new RuntimeException("数据被篡改！");

        }
        return "info";
    }

    public String myOrderList(HttpServletRequest request, HttpServletResponse response) throws Exception {
        try {
            User user = (User) request.getSession().getAttribute("user");
            String uid = user.getUid();
            String currPage = request.getParameter("currPage");
            Page page = orderService.findPageByUid(uid, currPage);
            request.setAttribute("page",page);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("msg","订单查看异常");
        }
        return "order_list";
    }
}
