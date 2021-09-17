package com.yaorange.jk.web.system;

import com.yaorange.jk.entity.Role;
import com.yaorange.jk.service.RoleService;
import com.yaorange.jk.utils.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/system/role")
public class RoleCtrl {
    @Autowired
    private RoleService roleService;
    @GetMapping
    public Pagination page(Integer pageNo, Integer pageSize) {
        return roleService.page(pageNo, pageSize);
    }
   @GetMapping("/all")
    public List<Role> findAll() {
        return roleService.findAll();
    }
    @PostMapping
    public String save(@RequestBody Role role) {
        roleService.save(role,role.getModuleIds());
        return "1";
    }
    @PutMapping
    public String update( @RequestBody Role role) {
         roleService.update(role,role.getModuleIds());
        return "1";
    }
    @DeleteMapping
    public String delete( @RequestBody String[] roleIds) {
        roleService.deleteByIds(roleIds);
        return "1";
    }
}
