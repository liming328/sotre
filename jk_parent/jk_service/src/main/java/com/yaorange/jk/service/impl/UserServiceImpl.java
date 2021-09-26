package com.yaorange.jk.service.impl;

import com.yaorange.jk.dao.BaseDao;
import com.yaorange.jk.entity.Role;
import com.yaorange.jk.entity.User;
import com.yaorange.jk.service.UserService;
import com.yaorange.jk.utils.JavaMailUtil;
import com.yaorange.jk.utils.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
/*@PreAuthorize("hasAuthority('用户管理')")*/
public class UserServiceImpl implements UserService {
    @Autowired
    private BaseDao<User,String> userDao;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Autowired
    private ThreadPoolTaskExecutor mailThreadPool;
    @Autowired
    private JavaMailUtil javaMailUtil;
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
        String password = "111111";
        user.setPassword(passwordEncoder.encode(password));
        userDao.save(user);
        //采用线程池优化,可以控制线程数量
        mailThreadPool.execute(new Runnable() {
            @Override
            public void run() {
                //考虑到网路波动，可能几秒断网时间。
                // 解决方案:发送失败，隔5秒再发，一共发3次。
                try {
                    if (null != user.getUserInfo().getEmail() && !"".equals(user.getUserInfo().getEmail())) {
                        javaMailUtil.sendMail(user.getUserInfo().getEmail(), user.getUserInfo().getName() + ":你好,欢迎加入 本公司", "你的账号密码是" + user.getUserName() + "/" + password);
                    }
                } catch (RuntimeException e) {
                    int count = 1;
                    boolean fail = true;
                    while (fail && count <= 3) {
                        try {
                            System.out.println("正在尝试" + count + "次发送邮件...");
                            //隔5秒再发
                            Thread.sleep(5000);
                            javaMailUtil.sendMail(user.getUserInfo().getEmail(), user.getUserInfo().getName() + ":你好,欢 迎加入本公司", "你的账号密码是" + user.getUserName() + "/" + password);
                            fail = false;
                            System.out.println("发送邮件成功。");
                        } catch (Exception e1) {
                            System.out.println("第" + count + "次发送邮件失败。");
                            e1.printStackTrace();
                            count++;
                        }
                    }
                }
            }
        });
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

    @Override
    public User findByUsername(String username) {
       return userDao.getByHQL("from User where userName = ?", username);
    }

}
