package com.yaorange.store.web.servlet.front;

import com.yaorange.store.utils.MybatisUtil;
import org.apache.ibatis.session.SqlSession;

public class Test1 {
    public static void main(String[] args) throws Exception {
        String username = "ssssdd";
       SqlSession sqlSession = MybatisUtil.getSqlSession();
    }
}

