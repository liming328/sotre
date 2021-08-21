package com.yaorange.store.web.servlet.front;

import com.yaorange.store.orm.User;
import com.yaorange.store.service.UserService;
import com.yaorange.store.service.impl.UserServiceImpl;
import com.yaorange.store.utils.MyDateConverter;
import com.yaorange.store.web.servlet.BaseServlet;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;

@WebServlet("/userServlet")
public class UserServlet extends BaseServlet {
    private UserService userService = new UserServiceImpl();

    /**
     * 检查用户名
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public String checkUsername(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String username = request.getParameter("username");
        User user = userService.findByUserName(username);
        PrintWriter out = response.getWriter();
        if (null != user) {
            out.write("1");
        }else {
            out.write("0");
        }
        return null;
    }

    /**
     * 注册
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public String register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //判断验证码
        String code = (String) request.getSession().getAttribute("code");
        String yzm = request.getParameter("yzm");
        if (null != yzm && !yzm.isEmpty() && yzm.equalsIgnoreCase(code)) {
            //获取表单数据
            User user = new User();
            try {
                ConvertUtils.register(new MyDateConverter(), Date.class);
                BeanUtils.populate(user,request.getParameterMap());
                boolean ok = userService.register(user);
                if (!ok){
                    request.setAttribute("errorMsg", "注册失败");
                    return "register";
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            request.setAttribute("errorMsg", "验证码错误");
            return "register";
        }
        return "redirect:login.jsp";
    }

    /**
     *激活用户
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public void activeUser(HttpServletRequest request, HttpServletResponse response) throws Exception {
        //1.获取激活码
        String code = request.getParameter("code");
        if (code !=null){
            //2.调用service完成激活
            UserService userService = new UserServiceImpl();
            boolean flag = userService.activeUser(code);
            //3.判断标记
            String msg=null;

            if (flag){
                //激活成功
                msg = "激活成功，请<a href='login.jsp'>登录</a>";
            }else {
                //激活失败
                msg = "激活失败或已激活";
            }
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().write(msg);
        }
    }

    /**
     * 登录用户
     * @param request
     * @param response
     * @throws Exception
     */
    public String login(HttpServletRequest request, HttpServletResponse response) throws Exception {
        //判断验证码
        String code = (String) request.getSession().getAttribute("code");
        String yzm = request.getParameter("yzm");
        if (null != yzm && !yzm.isEmpty() && yzm.equalsIgnoreCase(code)) {
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            boolean isOk = userService.login(username, password);
            if (isOk){
                //登录成功，判断是否记住用户名
                String remeberUsername = request.getParameter("remeberUsername");
                if (null!=remeberUsername){
                    //新建cookie
                    Cookie cookie = new Cookie("remeberUsername", username);
                    cookie.setMaxAge(60*60*24*7);
                    response.addCookie(cookie);
                }
                //判断是否自动登录
                String autoLogin = request.getParameter("autoLogin");
                if (null!=autoLogin){
                    Cookie cookie = new Cookie("autoLogin", username+"&&&"+password);
                    cookie.setMaxAge(60*60*24*7);
                    response.addCookie(cookie);
                }
                //保存user到session
                User user = userService.findByUserName(username);
                request.getSession().setAttribute("user",user);
                return "index";
            }else {
                request.setAttribute("msg","用户名或密码错误");
            }

        }else {
            request.setAttribute("msg","验证码错误");
        }
        return"login";
    }
}
