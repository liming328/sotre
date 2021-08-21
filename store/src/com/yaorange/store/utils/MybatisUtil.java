package com.yaorange.store.utils;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.Reader;

/**
 * MyBatis会话工具类
 *
 * @author runmu
 */
public class MybatisUtil {
    private static SqlSessionFactory sessionFactory ;

      static {
          try {
              Reader reader = Resources.getResourceAsReader("mybatis.xml");
              SqlSessionFactoryBuilder sessionFactoryBuilder = new SqlSessionFactoryBuilder();
              sessionFactory = sessionFactoryBuilder.build(reader);
              System.out.println("会话工厂创建成功。。。");
          } catch (IOException e) {
              System.out.println("核心配置文件加载异常。。。");
          }
      }





    /**
     * 获取会话
     *
     * @return
     */
    public static SqlSession getSqlSession() throws Exception {
        return sessionFactory.openSession();
    }

    /**
     * 关闭会话
     */
    public static void closeSqlSession(SqlSession sqlSession) {
        if (null != sqlSession) {
            sqlSession.close();
        }
    }
}













