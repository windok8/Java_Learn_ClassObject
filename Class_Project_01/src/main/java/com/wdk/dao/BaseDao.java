package com.wdk.dao;

import com.wdk.util.RecipeSystemConnectionPool;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author : Windok
 * @date: 2023-10-16
 * @Description:
 * @version: 1.0
 */
public abstract class BaseDao {


    public List baseQuery(Class clazz, String sql, Object... args) {
        // 查询名字中包含字母A的员工信息
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List list = null;
        try {
            connection = RecipeSystemConnectionPool.getConnection();
            preparedStatement = connection.prepareStatement(sql);//这里已经传入SQL语句
            //设置参数
            for (int i = 0; i < args.length; i++) {
                preparedStatement.setObject(i + 1, args[i]);
            }
            //执行CURD
            resultSet = preparedStatement.executeQuery();// 这里不需要再传入SQL语句
            list = new ArrayList();
            // 根据字节码获取所有 的属性
            Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields) {
                field.setAccessible(true);// 设置属性可以 访问
            }
            while (resultSet.next()) {
                // 通过反射创建对象
                Object obj = clazz.newInstance();//默认在通过反射调用对象的空参构造方法
                for (Field field : fields) {// 临时用Field设置属性
                    String fieldName = field.getName();// empno  ename job .... ...
                    Object data = resultSet.getObject(fieldName);
                    field.set(obj, data);
                }
                list.add(obj);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != resultSet) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (null != preparedStatement) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            RecipeSystemConnectionPool.returnConnection(connection);
        }
        return list;
    }

}