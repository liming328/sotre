package com.yaorange.store.web.servlet.admin;

import com.yaorange.store.entity.Category;
import com.yaorange.store.entity.Page;
import com.yaorange.store.entity.Product;
import com.yaorange.store.service.ProductService;
import com.yaorange.store.service.impl.CategoryServiceImpl;
import com.yaorange.store.service.impl.ProductServiceImpl;
import com.yaorange.store.utils.UuidUtils;
import com.yaorange.store.web.servlet.BaseServlet;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.Converter;
import org.apache.commons.io.IOUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.FileOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
@MultipartConfig //可接收带附件
@WebServlet("/AdminProductServlet")
public class AdminProductServlet extends BaseServlet {
    private ProductService productService = new ProductServiceImpl();
    private CategoryServiceImpl categoryService = new CategoryServiceImpl();
    public String findPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, Exception {
        try {
            String currPage = request.getParameter("currPage");
            Page page = productService.findPage(currPage);
            request.setAttribute("pageBean",page);
        } catch (Exception e) {
            request.setAttribute("msg","系统错误");
        }
        return "admin/product/list";
    }

    public String saveUI(HttpServletRequest request, HttpServletResponse response) throws ServletException, Exception {
        try {
            List<Category> categoryList = categoryService.findAll();
            request.setAttribute("list",categoryList);
        } catch (Exception e) {
            request.setAttribute("msg","系统错误");
        }
        return "admin/product/add";
    }
    public String pushDown(HttpServletRequest request, HttpServletResponse response) throws ServletException, Exception {
        try {
            String pid = request.getParameter("pid");
            String currPage = request.getParameter("currPage");
            String pflag = request.getParameter("pflag");
            if(pflag.equals("0")){
                pflag = "1";
            }else {
                pflag = "0";
            }
            productService.updatePfalg(pid,Integer.valueOf(pflag));
            Page page = productService.findPage(currPage);
            request.setAttribute("pageBean",page);
        } catch (Exception e) {
            request.setAttribute("msg","系统错误");
        }

        return findPage(request, response);
    }
    public String save(HttpServletRequest request, HttpServletResponse response) throws ServletException, Exception {
        Product product = new Product();
        try {
            ConvertUtils.register(new Converter() {
                @Override
                public Object convert(Class aClass, Object value) {
                    try {
                        return new SimpleDateFormat("yy-MM-dd").parse(value.toString());
                    } catch (ParseException e) {
                        return null;
                    }

                }
            }, Date.class);
            BeanUtils.populate(product,request.getParameterMap());
            //单独获取文件域
            Part uploadPart = request.getPart("upload");
            String filePath = "products/1/"+ UuidUtils.getUUID() +uploadPart.getSubmittedFileName();
            FileOutputStream outputStream = new FileOutputStream(request.getServletContext().getRealPath("/")+filePath);
            IOUtils.copy(uploadPart.getInputStream(),outputStream);
            product.setPid(UuidUtils.getUUID());
            product.setPimage(filePath);
            product.setPflag(0);
            productService.save(product);
        } catch (Exception e) {
            request.setAttribute("msg","添加商品错误");
            return "admin/product/add";
        }
        String page = findPage(request, response);
        return page;
    }
}
