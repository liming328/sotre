package com.yaorange.jk.web.system;

import com.yaorange.jk.entity.User;
import com.yaorange.jk.entity.vo.RoleIdsVO;
import com.yaorange.jk.service.UserService;

import com.yaorange.jk.utils.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.provider.token.ConsumerTokenServices;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
@RestController
@RequestMapping("/system/user")
public class UserCtrl {
    /*@Autowired
    @Qualifier("consumerTokenServices")
    private ConsumerTokenServices consumerTokenServices;*/
    @Autowired
    private UserService userService;
    @GetMapping
    public Pagination page(Integer pageNo, Integer pageSize) {
        Pagination page = userService.page(pageNo, pageSize);
        List<?> list = page.getList();
        return page;
    }
    @GetMapping("/all")
    public List<User> findAll() {
        return userService.findAll();
    }

    @PostMapping
    public String save(@RequestBody User user) {
        userService.save(user);
        return "1";
    }
    @PutMapping
    public String update(@RequestBody User user) {
        userService.update(user);
        return "1";
    }
    @PutMapping("/role")
    public String updateRole(@RequestBody RoleIdsVO roleIdsVO) {
        userService.updateRole(roleIdsVO.getId(),roleIdsVO.getRoleIds());
        return "1";
    }
    @DeleteMapping
    public String delete(@RequestBody String[] userIds) {
        userService.deleteByIds(userIds);
        return "1";
    }
    @DeleteMapping("/logout")
    public String logout(String access_token, HttpServletRequest req, HttpServletResponse res) {
        //清空token
        //consumerTokenServices.revokeToken(access_token);
        //清空spring security认证信息
        //Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        //if(authentication!=null){
         //   new SecurityContextLogoutHandler().logout(req,res,authentication);
        //}
        return "1";
    } @GetMapping("/info")

    public List<String> getLeftMenusByCurrentUser() {
        //获取当前用户信息
        User user = null;
        //通过spring security 工具知道当前登录用户
        org.springframework.security.core.userdetails.User currUser = (org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        //当前用户权限列表
        if (null==currUser){
            return null;
        }else {
            Collection<GrantedAuthority> authorities = currUser.getAuthorities();
            //将GrantedAuthority --> String
            List<String> permissions = new ArrayList<>(authorities.size());
            for (GrantedAuthority authority:authorities){
                permissions.add(authority.getAuthority());
            }
            return permissions;
        }

    }
}
