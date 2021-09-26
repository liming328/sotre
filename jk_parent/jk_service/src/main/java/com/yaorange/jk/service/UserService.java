package com.yaorange.jk.service;

import com.yaorange.jk.entity.User;
import com.yaorange.jk.utils.Pagination;

import java.util.List;

public interface UserService {
    Pagination page(Integer pageNo, Integer pageSize);

    List<User> findAll();

    /**
     * 添加用户暂时不指定角色
     * @param user
     */
    void save(User user);

    /**
     * 可(批量)删除用户
     * @param ids
     */
    void deleteByIds(String[] ids);
    /**
     * 修改用户暂不修改角色
     * @param user
     */
    void update(User user);

    /**
     * 修改用户所属的多个角色
     * @param id
     * @param roleIds
     */
    void updateRole(String id, String[] roleIds);

    /**
     * 登录
     * @param username
     * @return
     */
    User findByUsername(String username);
}
