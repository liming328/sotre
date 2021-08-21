package com.yaorange.store.service.impl;

import com.yaorange.store.dao.UserDao;
import com.yaorange.store.orm.User;
import com.yaorange.store.service.UserService;
import com.yaorange.store.utils.MailUtils;
import com.yaorange.store.utils.MybatisUtil;
import com.yaorange.store.utils.UuidUtils;
import org.apache.ibatis.session.SqlSession;

public class UserServiceImpl implements UserService {
    @Override
    public User findByUserName(String username)throws Exception {
        SqlSession sqlSession = null;
        try {
            sqlSession = MybatisUtil.getSqlSession();
            UserDao userDao = sqlSession.getMapper(UserDao.class);
            User user = userDao.findByUserName(username);
            return user;
        }
        finally {
            MybatisUtil.closeSqlSession(sqlSession);
        }


    }

    @Override
    public boolean register(User user) {
        boolean result = false;
        SqlSession sqlSession = null;
        try {
            sqlSession = MybatisUtil.getSqlSession();
            UserDao userDao = sqlSession.getMapper(UserDao.class);
            //新用户的某些属性是代码生成的
            user.setUid(UuidUtils.getUUID());
            user.setCode(UuidUtils.getUUID());
            user.setState(0);//0-未激活 1-激活
            userDao.save(user);
            //TODO发送邮箱
            //激活邮件发送，邮件正文？
            String content = "<a href='http://localhost/store/userServlet?method=activeUser&code=" + user.getCode() + "'>点击激活【store网】</a>";
            MailUtils.sendMail(user.getEmail(), content, "激活邮箱");
            sqlSession.commit();
            result = true;
        } catch (Exception e) {
           if (null!=sqlSession){
               sqlSession.rollback();
           }
        } finally {
            MybatisUtil.closeSqlSession(sqlSession);
            return result;
        }

    }

    @Override
    public boolean activeUser(String code) {
        boolean result = false;
        SqlSession sqlSession = null;
        try {
            sqlSession = MybatisUtil.getSqlSession();
            UserDao userDao = sqlSession.getMapper(UserDao.class);
            userDao.updateState(code);
            sqlSession.commit();
            result = true;
        } catch (Exception e) {
            if (null!=sqlSession){
                sqlSession.rollback();
            }
        } finally {
            MybatisUtil.closeSqlSession(sqlSession);
            return result;
        }

    }

    @Override
    public boolean login(String username, String password) throws Exception {
        boolean result = false;
        SqlSession sqlSession = null;
        try {
            sqlSession = MybatisUtil.getSqlSession();
            UserDao userDao = sqlSession.getMapper(UserDao.class);
            User user = userDao.findByUserName(username);
            if (user!=null){
                if (user.getPassword().equals(password)&&user.getState().equals(1)){
                    result = true;
                }

            }
        }
        finally {
            MybatisUtil.closeSqlSession(sqlSession);
            return result;
        }

    }
}
