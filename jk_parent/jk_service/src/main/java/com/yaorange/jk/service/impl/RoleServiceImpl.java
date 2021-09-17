package com.yaorange.jk.service.impl;

import com.yaorange.jk.dao.BaseDao;
import com.yaorange.jk.entity.Module;
import com.yaorange.jk.entity.Role;
import com.yaorange.jk.service.RoleService;
import com.yaorange.jk.utils.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private BaseDao<Role,String> roleDao;
    @Override
    public Pagination page(Integer pageNo, Integer pageSize) {
        return roleDao.pageByHql("from Role", pageNo, pageSize);
    }

    @Override
    public List<Role> findAll() {
        return roleDao.getListByHQL("from Role");
    }

    @Override
    public void save(Role role,String[] moduleIds) {
        //维护级联，设置角色和模块的多对多
        if (null!=moduleIds){
            for (String moduleId : moduleIds) {
                Module module = new Module();//只是临时装id,注意Role.hbm.xml中是设置,正常应该是查询
                module.setId(moduleId);
                role.getModuleSet().add(module);
            }
        }
        roleDao.save(role);
    }

    @Override
    public void deleteByIds(String[] ids) {
        for (String id : ids) {
            roleDao.deleteById(Role.class, id);
            //todo 删除角色关联的模块表
        }
    }

    @Override
    public void update(Role role, String[] moduleIds) {
      //将之前的模块清空--重新为该角色选模块
        role.getModuleSet().clear();
        //重新选
        if (null!=moduleIds){
            for (String moduleId : moduleIds) {
                Module module = new Module();//只是临时装id,注意Role.hbm.xml中是设置,正常应该是查询
                module.setId(moduleId);
                role.getModuleSet().add(module);
            }
        }
        roleDao.update(role);
    }

    @Override
    public Role findById(String roleId) {
        return roleDao.get(Role.class,roleId);
    }
}
