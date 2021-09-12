package com.yaorange.jk.service;

import com.yaorange.jk.entity.Dept;
import com.yaorange.jk.entity.Module;
import com.yaorange.jk.utils.Pagination;

import java.util.List;

public interface ModuleService {
    Pagination page(Integer pageNo, Integer pageSize);

    List<Module> findAll();

    void save(Module module);

    /**
     * 可(批量)删除模块
     * @param ids
     */
    void deleteByIds(String[] ids);

    void update(Module module);

    /**
     * 根据模块菜单类型获取模块列表
     * @param ctype 菜单类型 0-一级菜单 1-二级菜单
     * @return
     */
    List<Module> findListByCtype(Long ctype);
}
