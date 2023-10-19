package com.wdk.dao.impl;

import com.wdk.dao.CookBookDao;
import com.wdk.pojo.Account;
import com.wdk.pojo.CookBook;
import com.wdk.pojo.RecipeStatus;
import com.wdk.pojo.UserLevel;
import com.wdk.util.RecipeSystemConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author : Windok
 * @date: 2023-10-16
 * @Description:
 * @version: 1.0
 */
public class CookBookDaoImpl implements CookBookDao {

    @Override
    public CookBook getCookBookByRecipeID(int id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        CookBook cookBook = new CookBook(id);
        try {
            connection = RecipeSystemConnectionPool.getConnection();
            String sql = "SELECT * FROM recipe WHERE id = ?";
            preparedStatement = connection.prepareStatement(sql);//这里已经传入SQL语句
            preparedStatement.setObject(1, id);
            //执行CURD
            resultSet = preparedStatement.executeQuery();// 这里不需要再传入SQL语句
            if (resultSet.next()) {
                cookBook.setTitle(resultSet.getString("title"));
                cookBook.setDescription(resultSet.getString("description"));
                cookBook.setLikes(resultSet.getInt("likes"));
                cookBook.setCreateTime(resultSet.getDate("createtime"));
                cookBook.setUpdateTime(resultSet.getDate("updatetime"));
            }
            return cookBook;
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
    public Map<Integer, RecipeStatus> getAccountAndRecipeByuID(int id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Map<Integer, RecipeStatus> recipeStatusMap = new HashMap<>();
        try {
            connection = RecipeSystemConnectionPool.getConnection();
            String sql = "SELECT recipe_id, status FROM account_recipe WHERE account_id = ?";
            preparedStatement = connection.prepareStatement(sql);//这里已经传入SQL语句
            preparedStatement.setObject(1, id);
            //执行CURD
            resultSet = preparedStatement.executeQuery();// 这里不需要再传入SQL语句
            if (resultSet.next()) {
                int recipe_id = resultSet.getInt("recipe_id");
                RecipeStatus status = RecipeStatus.valueOf(resultSet.getString("status"));
                recipeStatusMap.put(recipe_id, status);
            }
            return recipeStatusMap;
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
