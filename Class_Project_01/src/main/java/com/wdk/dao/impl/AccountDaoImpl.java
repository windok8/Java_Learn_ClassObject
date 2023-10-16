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
                account.setUserLevel(
                        resultSet.getString("userlevel").equals("SUPER_ADMIN") ? UserLevel.SUPER_ADMIN :
                                resultSet.getString("userlevel").equals("ADMIN") ? UserLevel.ADMIN :
                                        resultSet.getString("userlevel").equals("AUTHOR") ? UserLevel.AUTHOR :
                                                resultSet.getString("userlevel").equals("USER") ? UserLevel.USER :
                                                        UserLevel.GUEST
                );
                System.out.println(account.getUserLevel().getDescription());
                account.setCreateTime(resultSet.getDate("createtime"));
                account.setLastLoginTime(resultSet.getDate("lastlogintime"));
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
}
