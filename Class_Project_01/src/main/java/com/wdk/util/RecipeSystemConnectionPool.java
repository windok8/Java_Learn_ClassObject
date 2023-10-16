package com.wdk.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.LinkedList;

/**
 * @author : Windok
 * @date: 2023-10-16
 * @Description:
 * @version: 1.0
 */
public class RecipeSystemConnectionPool {

    private static String driver;
    private static String url;
    private static String user;
    private static String password;
    private static int initSize;
    private static int maxSize;
    private static LinkedList<Connection> pool;

    static {
        // 初始化参数
        PropertiesUtil propertiesUtil = new PropertiesUtil("/jdbc.properties");
        driver = propertiesUtil.getProperties("mysqldb_driver");
        url = propertiesUtil.getProperties("mysqldb_url");
        user = propertiesUtil.getProperties("mysqldb_user");
        password = propertiesUtil.getProperties("mysqldb_password");
        initSize = Integer.parseInt(propertiesUtil.getProperties("mysqldb_initSize"));
        maxSize = Integer.parseInt(propertiesUtil.getProperties("mysqldb_maxSize"));
        // 加载驱动
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        // 初始化pool
        pool = new LinkedList<Connection>();
        // 创建5个链接对象
        for (int i = 0; i < initSize; i++) {
            Connection connection = initConnection();
            if (null != connection) {
                pool.add(connection);
                System.out.println("初始化连接" + connection.hashCode() + "放入连接池");
            }
        }
    }

    // 私有的初始化一个链接对象的方法
    private static Connection initConnection() {
        try {
            return DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // 共有的向外界提供链接对象的
    public static Connection getConnection() {
        Connection connection = null;
        if (pool.size() > 0) {
            connection = pool.removeFirst();// 移除集合中的第一个元素
            System.out.println("连接池中还有连接:" + connection.hashCode());
        } else {
            connection = initConnection();
            System.out.println("连接池空,创建新连接:" + connection.hashCode());
        }
        return connection;
    }

    // 共有的向连接池归还连接对象的方法
    public static void returnConnection(Connection connection) {
        if (null != connection) {
            try {
                if (!connection.isClosed()) {
                    if (pool.size() < maxSize) {
                        try {
                            connection.setAutoCommit(true);// 调整事务状态
                            System.out.println("设置连接:" + connection.hashCode() + "自动提交为true");
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                        pool.addLast(connection);
                        System.out.println("连接池未满,归还连接:" + connection.hashCode());
                    } else {
                        try {
                            connection.close();
                            System.out.println("连接池满了,关闭连接:" + connection.hashCode());
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }
                } else {
                    System.out.println("连接:" + connection.hashCode() + "已经关闭,无需归还");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("传入的连接为null,不可归还");
        }
    }


}
