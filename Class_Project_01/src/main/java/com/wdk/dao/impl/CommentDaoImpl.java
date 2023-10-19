package com.wdk.dao.impl;

import com.wdk.dao.CommentDao;
import com.wdk.pojo.Comment;
import com.wdk.util.RecipeSystemConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author : Windok
 * @date: 2023-10-17
 * @Description:
 * @version: 1.0
 */
public class CommentDaoImpl implements CommentDao {

    @Override
    public List<Comment> getCommitsByRecipeID(int cookBookID) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Comment> comments = new ArrayList<>();
        try {
            connection = RecipeSystemConnectionPool.getConnection();
            String sql = "SELECT * FROM recipe_comments WHERE recipe_id = ?";
            preparedStatement = connection.prepareStatement(sql);//这里已经传入SQL语句
            preparedStatement.setObject(1, cookBookID);
            //执行CURD
            resultSet = preparedStatement.executeQuery();// 这里不需要再传入SQL语句
            if (resultSet.next()) {
                Comment comment = new Comment(resultSet.getInt("comment_id"));
                comment.setCid(resultSet.getInt("recipe_id"));
                comment.setUid(resultSet.getInt("user_id"));
                comment.setContent(resultSet.getString("comment_text"));
                comment.setCreateTime(resultSet.getDate("comment_date"));
                comments.add(comment);
            }

            return comments;
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
