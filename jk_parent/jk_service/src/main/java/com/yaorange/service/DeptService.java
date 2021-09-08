package com.yaorange.service;

import com.yaorange.jk.entity.Dept;
import com.yaorange.jk.utils.Pagination;

import java.util.List;

public interface DeptService {
    /**
     * 分页查询部门信息
     * @param pageNo
     * @param pageSize
     * @return
     */
    Pagination page(Integer pageNo, Integer pageSize);

    List<Dept> findAll();

    void save(Dept dept);

    /**
     * 可(批量)删除部门
     * @param ids
     */
    void deleteByIds(String[] ids);

    void update(Dept dept);
}
