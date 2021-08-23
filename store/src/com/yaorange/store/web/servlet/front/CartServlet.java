package com.yaorange.store.web.servlet.front;

import com.yaorange.store.entity.CartItem;
import com.yaorange.store.entity.Product;
import com.yaorange.store.service.ProductService;
import com.yaorange.store.service.impl.ProductServiceImpl;
import com.yaorange.store.web.servlet.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/cartServlet")
public class CartServlet extends BaseServlet {
    private ProductService productService = new ProductServiceImpl();

    /**
     * 加入购物车
     * @param request
     * @param response
     * @return
     * @throws ServletException
     * @throws Exception
     */
    public String addCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, Exception {
        String pid = request.getParameter("pid");
        String count = request.getParameter("count");
        HashMap<String, CartItem> cart = (HashMap<String, CartItem>) request.getSession().getAttribute("cart");
        try {
            Product product = productService.findByPid(pid);
            CartItem cartItem = new CartItem();
            //判断有无购物车
            if (null != cart) {
                if (cart.containsKey(pid)) {
                    cartItem = cart.get(pid);
                    cartItem.setCount(cartItem.getCount() + Integer.parseInt(count));
                } else {
                    cartItem.setProduct(product);
                    cartItem.setCount(Integer.valueOf(count));
                    cart.put(pid, cartItem);
                }

            } else {
                //没有车
                cart = new HashMap<>();
                cartItem.setProduct(product);
                cartItem.setCount(Integer.valueOf(count));
                cart.put(pid, cartItem);
                request.getSession().setAttribute("cart", cart);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:cart.jsp";
    }

    /**
     * 更新ajax购物车条目中的数量
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void updateCount(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pid = request.getParameter("pid");
        String count = request.getParameter("count");
        HashMap<String, CartItem> cart = (HashMap<String, CartItem>) request.getSession().getAttribute("cart");
        CartItem cartItem = cart.get(pid);
        cartItem.setCount(Integer.valueOf(count));
        Double total = 0.0;
        for (Map.Entry<String, CartItem> entry : cart.entrySet()) {
            total+=entry.getValue().getSubtotal();
        }
        String result = cartItem.getSubtotal() + "&&&" + total;
        PrintWriter out = response.getWriter();
        out.write(result);
        out.flush();
        out.close();
    }

    /**
     * 删除购物车条目
     * @param request
     * @param response
     * @return
     * @throws ServletException
     * @throws IOException
     */
    public String deleteCartItem(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pid = request.getParameter("pid");
        HashMap<String, CartItem> cart = (HashMap<String, CartItem>) request.getSession().getAttribute("cart");
        CartItem cartItem = cart.get(pid);
        if (null!=pid){
            cart.remove(pid, cartItem);
        }
        return "cart";
    }

    /**
     * 清空购物车
     * @param request
     * @param response
     * @return
     * @throws ServletException
     * @throws IOException
     */
    public String clear(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pid = request.getParameter("pid");
        HashMap<String, CartItem> cart = (HashMap<String, CartItem>) request.getSession().getAttribute("cart");
        cart.clear();
        return "cart";
    }
}
