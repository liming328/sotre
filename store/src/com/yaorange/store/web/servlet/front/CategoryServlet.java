package com.yaorange.store.web.servlet.front;

import com.alibaba.fastjson.JSON;
import com.yaorange.store.entity.Category;
import com.yaorange.store.service.CategoryService;
import com.yaorange.store.service.impl.CategoryServiceImpl;
import com.yaorange.store.web.servlet.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/categoryServlet")
public class CategoryServlet extends BaseServlet {
    private CategoryService categoryService = new CategoryServiceImpl();

    public void findAll(HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
        try {
            List<Category> categoryList = categoryService.findAll();
            //种类集合-》json
            String json = JSON.toJSONString(categoryList);
            response.setContentType("test/json;charset=utf-8");
            PrintWriter out = response.getWriter();
            out.write(json);
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
