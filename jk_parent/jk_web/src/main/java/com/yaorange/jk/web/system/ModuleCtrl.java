package com.yaorange.jk.web.system;

import com.yaorange.jk.entity.Module;
import com.yaorange.jk.service.ModuleService;
import com.yaorange.jk.utils.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 部门前端控制器
 */
@RestController
@RequestMapping("/system/module")
public class ModuleCtrl {
    @Autowired
    private ModuleService moduleService;

   @GetMapping
    public Pagination page(Integer pageNo, Integer pageSize) {
        return moduleService.page(pageNo, pageSize);
    }
    @GetMapping("/all")
    public List<Module> findAll() {
        return moduleService.findAll();
    }

    @GetMapping("/getParent/{ctype}")
    public List<Module> getParentList(@PathVariable("ctype") Long ctype) {
        return moduleService.findListByCtype(ctype-1L);
    }
    @PostMapping
    public String save(@RequestBody Module module) {
        moduleService.save(module);
        return "1";
    }
    @PutMapping
    public String update(@RequestBody Module module) {
        moduleService.update(module);
        return "1";
    }

   @DeleteMapping
    public String delete(@RequestBody String[] ids){
       moduleService.deleteByIds(ids);
        return "1";
    }

}
