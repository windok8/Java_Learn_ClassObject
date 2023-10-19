package com.wdk.dao.impl;

import com.wdk.dao.AccountDao;
import com.wdk.dao.BaseDao;
import com.wdk.pojo.Account;
import com.wdk.pojo.UserLevel;
import com.wdk.util.RecipeSystemConnectionPool;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


/**
 * @author : Windok
 * @date: 2023-10-10
 * @Description:
 * @version: 1.0
 */
public class AccountDaoImpl extends BaseDao implements AccountDao {

    @Override
    public int checkAccount(String username, String password) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        int account_ID = -1;
        try {
            connection = RecipeSystemConnectionPool.getConnection();
            String sql = "SELECT id FROM account WHERE username = ? AND password = ?";
            preparedStatement = connection.prepareStatement(sql);//这里已经传入SQL语句
            preparedStatement.setObject(1, username);
            preparedStatement.setObject(2, password);
            //执行CURD
            resultSet = preparedStatement.executeQuery();// 这里不需要再传入SQL语句
            if (resultSet.next()) {
                account_ID = resultSet.getInt("id");  // 如果存在匹配的记录，将账户ID设置为结果
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
            if (null != connection) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return account_ID;
    }

    @Override
    public Account getAccountById(int id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Account account = new Account(id);
        try {
            connection = RecipeSystemConnectionPool.getConnection();
            String sql = "SELECT * FROM account WHERE id = ?";
            preparedStatement = connection.prepareStatement(sql);//这里已经传入SQL语句
            preparedStatement.setObject(1, id);
            //执行CURD
            resultSet = preparedStatement.executeQuery();// 这里不需要再传入SQL语句
            if (resultSet.next()) {
                account.setUsername(resultSet.getString("username"));
                account.setPassword(resultSet.getString("password"));
                String userLevel = resultSet.getString("userlevel");
                account.setUserLevel(UserLevel.valueOf(userLevel));
                account.setCreateTime(resultSet.getDate("createtime"));
                account.setLastLoginTime(resultSet.getDate("lastlogintime"));
                if(resultSet.getBoolean("is_deleted")){
                    return null;
                }
            }

            return account;
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
    public int insertAccount(Account account) {
        Connection connection = null;
        PreparedStatement preparedStatement=null;
        int rows=0;
        try{
            connection = RecipeSystemConnectionPool.getConnection();
            String sql="insert into account(id,username,password,userlevel,createtime,lastlogintime) values(?,?,?,?,?,?)";
            preparedStatement = connection.prepareStatement(sql);//这里已经传入SQL语句
            //设置参数
            preparedStatement.setObject(1,account.getId());
            preparedStatement.setObject(2,account.getUsername());
            preparedStatement.setObject(3,account.getPassword());
            preparedStatement.setObject(4,account.getUserLevel().name());
            preparedStatement.setObject(5,account.getCreateTime());
            preparedStatement.setObject(6,account.getLastLoginTime());
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

    @Override
    public int updatePassword(int accountID, String newPassword) {
        Connection connection = null;
        PreparedStatement preparedStatement=null;
        int rows=0;
        try{
            connection = RecipeSystemConnectionPool.getConnection();
            String sql="update account set password = ? where id = ?";
            preparedStatement = connection.prepareStatement(sql);//这里已经传入SQL语句
            //设置参数
            preparedStatement.setObject(1,newPassword);
            preparedStatement.setObject(2,accountID);
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
