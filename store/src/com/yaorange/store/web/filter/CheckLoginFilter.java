package com.yaorange.store.web.filter;


import com.yaorange.store.entity.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
@WebFilter(urlPatterns = {"/cartServlet","/orderServlet"})
public class CheckLoginFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("检查是否登录。。。。");
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response=(HttpServletResponse) servletResponse;
        request.setCharacterEncoding("utf-8");
        //判断是否已经登录
        User user = (User) request.getSession().getAttribute("user");
        if (null==user){
            response.setContentType("text/html;charset=utf-8");
            PrintWriter out = response.getWriter();
            out.print("<script type='text/javascript'>");
            out.print("alert('用户未登录');");
            out.print("location.href='login.jsp';");
            out.print("</script>");
            return;
        }else {
            //放行
            filterChain.doFilter(request,response);
        }

    }

    @Override
    public void destroy() {

    }
}
