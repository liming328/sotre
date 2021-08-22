package com.yaorange.store.service;

import com.yaorange.store.entity.User;

public interface UserService {
    /**
     * 根据用户名查找用户
     * @param username
     * @return
     */
    User findByUserName(String username)throws Exception;

    /**
     * 新用户注册
     *
     * @param user
     * @return
     */
    boolean register(User user)throws Exception;

    /**
     * 激活用户
     * @param code
     * @return
     */
    boolean activeUser(String code)throws Exception;

    /**
     * 用户登录
     * @param username
     * @param password
     * @return
     */
    boolean login(String username, String password)throws Exception;
}
