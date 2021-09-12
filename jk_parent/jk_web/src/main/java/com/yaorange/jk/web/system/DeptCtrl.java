package com.yaorange.jk.web.system;

import com.yaorange.jk.entity.Dept;
import com.yaorange.jk.service.DeptService;

import com.yaorange.jk.utils.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 部门前端控制器
 */
@RestController
@RequestMapping("/system/dept")
public class DeptCtrl {
    /**
     *
     */
    @Autowired
    private DeptService deptService;
    @GetMapping
    public Pagination page(Integer pageNo, Integer pageSize) {
        Pagination page = deptService.page(pageNo, pageSize);
        return page;
    }
    @GetMapping("/all")
    public List<Dept> findAll() {
        return deptService.findAll();
    }
    @PostMapping
    public String save(@RequestBody Dept dept){
        deptService.save(dept);
        return "1";
    }
    @PutMapping
    public String update(@RequestBody Dept dept){
        deptService.update(dept);
        return "1";
    }
    @DeleteMapping
    public String delete(@RequestBody String[] ids){
        deptService.deleteByIds(ids);
        return "1";
    }
}
