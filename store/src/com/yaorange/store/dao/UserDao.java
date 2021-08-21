package com.yaorange.store.dao;

import com.yaorange.store.orm.User;

public interface UserDao {
    /**
     * 根据用户名查找用户
     * @param username
     * @return
     */
    User findByUserName(String username)throws Exception;

    /**
     * 添加新用户
     * @param user
     */
    void save(User user)throws Exception;

    /**
     * 修改用户状态
     * @param code
     * @return
     */
    int updateState(String code)throws Exception;
}
