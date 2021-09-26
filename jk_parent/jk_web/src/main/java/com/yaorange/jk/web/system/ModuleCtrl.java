package com.yaorange.jk.web.system;

import com.yaorange.jk.entity.Module;
import com.yaorange.jk.entity.Role;
import com.yaorange.jk.entity.vo.ZtreeVO;
import com.yaorange.jk.service.ModuleService;
import com.yaorange.jk.service.RoleService;
import com.yaorange.jk.utils.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * 部门前端控制器
 */
@RestController
@RequestMapping("/system/module")
public class ModuleCtrl {
    @Autowired
    private ModuleService moduleService;
    @Autowired
    private RoleService roleService;

   @GetMapping
    public Pagination page(Integer pageNo, Integer pageSize) {
        return moduleService.page(pageNo, pageSize);
    }
    @GetMapping("/all")
    public List<Module> findAll() {
        return moduleService.findAll();
    }
    @GetMapping("/allZTreeNode")
    public List<ZtreeVO> findAllZtreeVO() {
        List<Module> moduleList = moduleService.findAll();
        ArrayList<ZtreeVO> ztreeVOList = new ArrayList<>(moduleList.size());
        for (Module module : moduleList) {
            ZtreeVO ztreeVO = new ZtreeVO();
            ztreeVO.setId(module.getId());
            ztreeVO.setpId(module.getParentId()==null?"0": module.getParentId());
            ztreeVO.setName(module.getName());
            ztreeVO.setChecked(false);
            ztreeVO.setOpen(true);
            ztreeVOList.add(ztreeVO);
        }
        return ztreeVOList;
    }
    @GetMapping("/allZTreeNode/{roleId}")
    public List<ZtreeVO> findAllZtreeByRoleId(@PathVariable String roleId) {
        List<Module> moduleList = moduleService.findAll();
        Role role = roleService.findById(roleId);
        Set<Module> moduleSet = role.getModuleSet();
        ArrayList<ZtreeVO> ztreeVOList = new ArrayList<>(moduleSet.size());
        for (Module module : moduleList) {
            ZtreeVO ztreeVO = new ZtreeVO();
            ztreeVO.setId(module.getId());
            if (moduleSet.contains(module)){
                ztreeVO.setChecked(true);
            }else {
                ztreeVO.setChecked(false);
            }
            ztreeVO.setpId(module.getParentId()==null?"0": module.getParentId());
            ztreeVO.setName(module.getName());
            ztreeVO.setOpen(true);
            ztreeVOList.add(ztreeVO);
        }
        return ztreeVOList;
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
