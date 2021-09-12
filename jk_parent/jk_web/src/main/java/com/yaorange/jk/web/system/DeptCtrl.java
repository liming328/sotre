package com.yaorange.jk.web.system;

import com.yaorange.jk.entity.dept;
import com.yaorange.jk.service.deptService;
import com.yaorange.jk.utils.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 部门前端控制器
 */
@RestController
@RequestMapping("/system/dept")
public class deptCtrl {
    @Autowired
    private deptService deptService;
    @GetMapping
    public Pagination page(Integer pageNo, Integer pageSize) {
        Pagination page = deptService.page(pageNo, pageSize);
        return page;
    }
    @GetMapping("/all")
    public List<dept> findAll() {
        return deptService.findAll();
    }
    @PostMapping
    public String save(@RequestBody dept dept){
        deptService.save(dept);
        return "1";
    }
    @PutMapping
    public String update(@RequestBody dept dept){
        deptService.update(dept);
        return "1";
    }
    @DeleteMapping
    public String delete(@RequestBody String[] ids){
        deptService.deleteByIds(ids);
        return "1";
    }
}
