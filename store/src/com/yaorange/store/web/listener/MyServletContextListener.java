package com.yaorange.store.web.listener;

import com.mysql.jdbc.AbandonedConnectionCleanupThread;
import com.yaorange.store.utils.CacheUtil;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;
@WebListener
public class MyServletContextListener implements ServletContextListener {
    private java.sql.Driver driver = null;
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {

    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        ClassLoader cl = Thread.currentThread().getContextClassLoader();
        //这里如果web拥有多个数据库的连接，可以一并关闭
        Enumeration<Driver> drivers = DriverManager.getDrivers();
        while (drivers.hasMoreElements()){
            try {
                driver = drivers.nextElement();
                DriverManager.deregisterDriver(driver);
                CacheUtil.closeCacheManager();
                AbandonedConnectionCleanupThread.shutdown();
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
