package com.yaorange.jk.service;

import com.yaorange.jk.entity.Role;
import com.yaorange.jk.utils.Pagination;

import java.util.List;

public interface RoleService {
    Pagination page(Integer pageNo, Integer pageSize);

    List<Role> findAll();

    /**
     * 保存角色并指定角色所持有的模块
     * @param role
     * @param moduleIds
     */
    void save(Role role,String[] moduleIds);

    /**
     * 可(批量)删除模块
     * @param ids
     */
    void deleteByIds(String[] ids);
    /**
     * 修改角色并重新指定角色所持有的模块
     * @param role
     * @param moduleIds
     */
    void update(Role role,String[] moduleIds);

    Role findById(String roleId);
}
