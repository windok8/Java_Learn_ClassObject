package com.wdk.dao.impl;

import com.wdk.dao.UserDao;
import com.wdk.pojo.Account;
import com.wdk.pojo.GenderEnum;
import com.wdk.pojo.User;
import com.wdk.pojo.UserLevel;
import com.wdk.util.RecipeSystemConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author : Windok
 * @date: 2023-10-16
 * @Description:
 * @version: 1.0
 */
public class UserDaoImpl implements UserDao {
    @Override
    public User getUserById(int id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        UserLevel userGender = null;
        User user = new User();
        try {
            connection = RecipeSystemConnectionPool.getConnection();
            String sql = "SELECT * FROM user WHERE id = ?";
            preparedStatement = connection.prepareStatement(sql);//这里已经传入SQL语句
            preparedStatement.setObject(1, id);
            //执行CURD
            resultSet = preparedStatement.executeQuery();// 这里不需要再传入SQL语句
            if (resultSet.next()) {
                user.setUserName(resultSet.getString("username"));
                user.setRealName(resultSet.getString("realname"));
                user.setGender(GenderEnum.valueOf(resultSet.getString("gender")));
                user.setPhone(resultSet.getString("phone"));
                user.setEmail(resultSet.getString("email"));
            }
            return user;
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
            if (null != connection) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    @Override
    public int insertUser(int id,User user) {
        Connection connection = null;
        PreparedStatement preparedStatement=null;
        int rows=0;
        try{
            connection = RecipeSystemConnectionPool.getConnection();
            String sql="insert into user values(?,?,?,?,?,?)";
            preparedStatement = connection.prepareStatement(sql);//这里已经传入SQL语句
            //设置参数
            preparedStatement.setObject(1,id);
            preparedStatement.setObject(2,user.getUserName());
            preparedStatement.setObject(3,user.getRealName());
            preparedStatement.setObject(4,user.getGender().name());
            preparedStatement.setObject(5,user.getPhone());
            preparedStatement.setObject(6,user.getEmail());
            //执行CURD
            rows =preparedStatement.executeUpdate();// 这里不需要再传入SQL语句
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(null != preparedStatement){
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(null != connection){
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return rows;
    }
}
