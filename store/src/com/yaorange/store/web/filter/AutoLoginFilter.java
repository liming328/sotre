package com.yaorange.store.web.filter;

import com.yaorange.store.entity.User;
import com.yaorange.store.service.UserService;
import com.yaorange.store.service.impl.UserServiceImpl;
import com.yaorange.store.utils.CookieUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@WebFilter("/*")
public class AutoLoginFilter implements Filter {
    private UserService userService = new UserServiceImpl();
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("自动登录检查。。。。");
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response=(HttpServletResponse) servletResponse;
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        //判断是否已经登录
        User user = (User) request.getSession().getAttribute("user");
        if (null == user) {
            //判断是否需要自动登录
            Cookie cookie = CookieUtils.getCookie("autoLogin", request.getCookies());
            if (null != cookie) {
                String value = cookie.getValue();
                String[] values = value.split("&&&");
                String username = values[0];
                String password = values[1];
                //TODO自动登录bug
                try {
                    user = userService.findByUserName(username);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                request.getSession().setAttribute("user",user);
            }
        }
        //放行
        filterChain.doFilter(request,response);
    }

    @Override
    public void destroy() {

    }
}
