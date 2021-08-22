package com.yaorange.store.web.servlet.front;

import com.alibaba.fastjson.JSON;
import com.yaorange.store.orm.Page;
import com.yaorange.store.orm.Product;
import com.yaorange.store.service.ProductService;
import com.yaorange.store.service.impl.ProductServiceImpl;
import com.yaorange.store.web.servlet.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/productServlet")
public class ProductServlet extends BaseServlet {
    private ProductService productService = new ProductServiceImpl();
    public void findHotProductList(HttpServletRequest request, HttpServletResponse response) throws ServletException, Exception {

        try {
            List<Product> hotProductList = productService.findHotProductList();
            String json = JSON.toJSONString(hotProductList);
            response.setContentType("text/html;charset=utf-8");
            PrintWriter out = response.getWriter();
            out.write(json);
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public String findPageByCid(HttpServletRequest request, HttpServletResponse response) throws ServletException, Exception {

        try {
            String cid = request.getParameter("cid");
            String currPage = request.getParameter("currPage");
            Page page = productService.findPageByCid(cid, currPage);
            request.setAttribute("page",page);
            return "product_list";
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public String findByPid(HttpServletRequest request, HttpServletResponse response) throws ServletException, Exception {
        try {
            String pid = request.getParameter("pid");
            Product product = productService.findByPid(pid);
            request.setAttribute("product",product);
            return "product_info";
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
