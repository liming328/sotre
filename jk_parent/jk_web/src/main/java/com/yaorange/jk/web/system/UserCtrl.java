package com.yaorange.jk.web.system;

import com.yaorange.jk.entity.User;
import com.yaorange.jk.entity.vo.RoleIdsVO;
import com.yaorange.jk.service.UserService;
import com.yaorange.jk.utils.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/system/user")
public class UserCtrl {

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
}
