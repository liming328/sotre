package com.yaorange.jk.service.impl;

import com.yaorange.jk.dao.BaseDao;
import com.yaorange.jk.entity.Dept;
import com.yaorange.jk.entity.Role;
import com.yaorange.jk.entity.User;
import com.yaorange.jk.service.UserService;
import com.yaorange.jk.utils.Pagination;
import com.yaorange.jk.utils.UuidUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private BaseDao<User,String> userDao;
    @Override
    public Pagination page(Integer pageNo, Integer pageSize) {
        return userDao.pageByHql("from User", pageNo, pageSize);
    }

    @Override
    public List<User> findAll() {
        return userDao.getListByHQL("from User");
    }

    @Override
    public void save(User user) {
        //前端要传递managerId 视图属性
        User manager = new User();
        manager.setId(user.getManagerId());
        //many-to-one绑定直属领导
        user.getUserInfo().setManager(manager);
        //one-to-one绑定附加属性对象
        user.getUserInfo().setUser(user);
        userDao.save(user);
    }

    @Override
    public void deleteByIds(String[] ids) {
        for (String id : ids) {
            userDao.deleteById(User.class, id);
            //todo 删除角色关联的模块表
        }
    }

    @Override
    public void update(User user) {
        user.getUserInfo().setUser(user);
        user.getUserInfo().setId(user.getId());
        //从数据库查询要修改的原始数据
        User dbUser = userDao.get(User.class, user.getId());
        //因为新建用户生成原始数据某些不会出现在修改页面上，比如createTime..
        //同步数据库才不会报错，因为这些都是非空的
        user.setCreateTime(dbUser.getCreateTime());
        user.setUpdateTime(new Date());
        user.getUserInfo().setCreateTime(dbUser.getUserInfo().getCreateTime());
        user.getUserInfo().setUpdateTime(user.getUpdateTime());
        //附加操作再hib..去掉一级缓存dbUser
        userDao.evict(dbUser);
        userDao.update(user);
    }

    @Override
    public void updateRole(String id, String[] roleIds) {
        User user = userDao.get(User.class, id);
        //先清空原来的角色列表
        user.getRoleSet().clear();
        //再重新为用户赋值角色
        for (String roleId : roleIds) {
            Role role = new Role();
            role.setId(roleId);
            user.getRoleSet().add(role);
        }
        userDao.update(user);
    }

}
