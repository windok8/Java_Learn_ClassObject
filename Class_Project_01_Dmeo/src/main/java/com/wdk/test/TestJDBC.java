package com.wdk.test;

import java.sql.*;

/**
 * @author : Windok
 * @date: 2023-10-08
 * @Description:
 * @version: 1.0
 */
public class TestJDBC {

    public static void main(String[] args) throws SQLException {
        //  1.加载驱动 Driver
        Driver driver = new com.mysql.cj.jdbc.Driver();
        //  2.注册驱动 DriverManager
        DriverManager.registerDriver(driver);
        //  3.获取连接
        String url = "jdbc:mysql://localhost:3306/cookbook?useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia/Shanghai&useSSL=false";
        String user = "root";
        String password = "123456";
        Connection connection = DriverManager.getConnection(url, user, password);
        //  4.获取操作数据库的预处理对象
        Statement statement = connection.createStatement();
        //  5.执行SQL语句
        //  6.处理结果集
        //  7.释放资源
        String sql = "select * from account";
        ResultSet resultSet = statement.executeQuery(sql);

        connection.close();



    }

}
