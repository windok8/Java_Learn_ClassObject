package com.wdk.dao.impl;

import com.wdk.dao.CookBookDao;
import com.wdk.pojo.CookBook;
import com.wdk.pojo.RecipeStatus;
import com.wdk.util.RecipeSystemConnectionPool;
import com.wdk.util.StringAndMapConverter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

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
            while (resultSet.next()) {
                cookBook.setTitle(resultSet.getString("title"));
                Map<Integer, String> recipeDescription = new HashMap<>();
                String description = resultSet.getString("description");
                if (description != null){
                    recipeDescription = StringAndMapConverter.parseRecipeDescription(description);
                    cookBook.setSteps(recipeDescription.size());
                }
                cookBook.setDescription(recipeDescription);
                cookBook.setLikes(resultSet.getInt("likes"));
                cookBook.setCreateTime(resultSet.getDate("createtime"));
                cookBook.setUpdateTime(resultSet.getDate("updatetime"));
                String materials = resultSet.getString("materials");
                if (materials != null) cookBook.setMaterials(StringAndMapConverter.convertStringToMap(materials));
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
            // TODO:    SELECT * FROM account_recipe LEFT JOIN recipe ON account_recipe.recipe_id = recipe.id WHERE account_id = 562916
            String sql = "SELECT recipe_id, status FROM account_recipe WHERE account_id = ?";
            preparedStatement = connection.prepareStatement(sql);//这里已经传入SQL语句
            preparedStatement.setObject(1, id);
            //执行CURD
            resultSet = preparedStatement.executeQuery();// 这里不需要再传入SQL语句
            while (resultSet.next()) {
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

    @Override
    public int modifyContent(int id, String content, int recipe_columns) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        String sql = null;
        int rows = 0;
        Map<Integer, RecipeStatus> recipeStatusMap = new HashMap<>();
        try {
            connection = RecipeSystemConnectionPool.getConnection();
            switch (recipe_columns) {
                case 1:
                    sql = "update recipe set title = ? where id = ?";
                    break;
                case 2:
                    sql = "update recipe set description = ? where id = ?";
                    break;
                case 3:
                    sql = "update recipe set materials = ? where id = ?";
                    break;
                case 4:
                    sql = "update recipe set steps = ? where id = ?";
                    break;
            }
            preparedStatement = connection.prepareStatement(sql);//这里已经传入SQL语句

            preparedStatement.setObject(1, content);
            preparedStatement.setObject(2, id);

            rows = preparedStatement.executeUpdate();
            //  更新菜谱信息后修改 -> 更新时间
            sql = "update recipe set updatetime = ? where id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setObject(1, new Date());
            preparedStatement.setObject(2, id);
            preparedStatement.executeUpdate();
            //执行CURD
            // 这里不需要再传入SQL语句
            return rows;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
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
        return rows;
    }

    @Override
    public List<CookBook> getRecipe() {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<CookBook> recipeList = new ArrayList<>();
        try {
            connection = RecipeSystemConnectionPool.getConnection();
            String sql = "SELECT \n" +
                    "    recipe.id AS recipe_id,\n" +
                    "    recipe.title,\n" +
                    "    recipe.description,\n" +
                    "    recipe.likes,\n" +
                    "    recipe.createtime,\n" +
                    "    recipe.updatetime,\n" +
                    "    recipe.materials,\n" +
                    "    recipe.steps,\n" +
                    "    user.username,\n" +
                    "    user.email,\n" +
                    "\t\taccount_recipe.account_id,\n" +
                    "    account_recipe.status,\n" +
                    "    COUNT(recipe_comments.comment_id) AS comment_count\n" +
                    "FROM \n" +
                    "    recipe\n" +
                    "JOIN \n" +
                    "    account_recipe ON recipe.id = account_recipe.recipe_id\n" +
                    "JOIN \n" +
                    "    user ON account_recipe.account_id = user.id\n" +
                    "LEFT JOIN \n" +
                    "    recipe_comments ON recipe.id = recipe_comments.recipe_id\n" +
                    "WHERE\n" +
                    "\t\t`status` = 'APPROVED'\n" +
                    "GROUP BY \n" +
                    "    recipe.id,\n" +
                    "    user.username,\n" +
                    "    user.email,\n" +
                    "    account_recipe.status,\n" +
                    "\t\taccount_recipe.account_id";
            preparedStatement = connection.prepareStatement(sql);//这里已经传入SQL语句
            //执行CURD
            resultSet = preparedStatement.executeQuery();// 这里不需要再传入SQL语句
            while (resultSet.next()) {
                CookBook cookBook = new CookBook(resultSet.getInt("recipe_id"));
                cookBook.setTitle(resultSet.getString("title"));
                Map<Integer, String> recipeDescription = new HashMap<>();
                String description = resultSet.getString("description");
                if (description != null){
                    recipeDescription = StringAndMapConverter.parseRecipeDescription(description);
                    cookBook.setSteps(recipeDescription.size());
                }
                cookBook.setDescription(recipeDescription);
                cookBook.setLikes(resultSet.getInt("likes"));
                cookBook.setCreateTime(resultSet.getDate("createtime"));
                cookBook.setUpdateTime(resultSet.getDate("updatetime"));
                cookBook.setSteps(resultSet.getInt("steps"));
                cookBook.setAuthorName(resultSet.getString("username"));
                cookBook.setCommentCount(resultSet.getInt("comment_count"));
                cookBook.setUserEmail(resultSet.getString("email"));
                cookBook.setUserID(resultSet.getInt("account_id"));
                String materials = resultSet.getString("materials");
                if (materials != null) cookBook.setMaterials(StringAndMapConverter.convertStringToMap(materials));
                recipeList.add(cookBook);
            }
            return recipeList;
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
    public List<CookBook> getRecipe_Admin() {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<CookBook> recipeList = new ArrayList<>();
        try {
            connection = RecipeSystemConnectionPool.getConnection();
            String sql = "SELECT recipe.id,title,createtime,updatetime,account_id,`status`,realname FROM recipe JOIN account_recipe ON recipe.id = account_recipe.recipe_id JOIN `user` ON `user`.id = account_recipe.account_id";
            preparedStatement = connection.prepareStatement(sql);//这里已经传入SQL语句
            //执行CURD
            resultSet = preparedStatement.executeQuery();// 这里不需要再传入SQL语句
            while (resultSet.next()) {
                CookBook cookBook = new CookBook(resultSet.getInt("id"));
                cookBook.setTitle(resultSet.getString("title"));
                cookBook.setCreateTime(resultSet.getDate("createtime"));
                cookBook.setUpdateTime(resultSet.getDate("updatetime"));
                cookBook.setAuthorRealName(resultSet.getString("realname"));
                cookBook.setUserID(resultSet.getInt("account_id"));
                cookBook.setStatus(RecipeStatus.valueOf(resultSet.getString("status")));
                recipeList.add(cookBook);
            }
            return recipeList;
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
    public int modifyRecipeStatus(int id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        int rows = 0;
        try {
            connection = RecipeSystemConnectionPool.getConnection();
            String sql = "update account_recipe set status = ? where id = ?";
            preparedStatement = connection.prepareStatement(sql);//这里已经传入SQL语句
            preparedStatement.setObject(1, RecipeStatus.PENDING.name());
            preparedStatement.setObject(2, id);
            //执行CURD
            rows = preparedStatement.executeUpdate();// 这里不需要再传入SQL语句
            return rows;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
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
        return rows;
    }

    @Override
    public boolean deleteRecipe(int id) {
        Connection connection = null;
        boolean success = false;

        try {
            connection = RecipeSystemConnectionPool.getConnection();
            connection.setAutoCommit(false); // 开启事务

            try {
                // 删除recipe_comments表中相关记录
                String deleteRecipeComments = "DELETE FROM recipe_comments WHERE recipe_id = ?";
                PreparedStatement deleteRecipeCommentsStatement = connection.prepareStatement(deleteRecipeComments);
                deleteRecipeCommentsStatement.setInt(1, id);
                deleteRecipeCommentsStatement.executeUpdate();

                // 删除account_recipe表中相关记录
                String deleteAccountRecipe = "DELETE FROM account_recipe WHERE recipe_id = ?";
                PreparedStatement deleteAccountRecipeStatement = connection.prepareStatement(deleteAccountRecipe);
                deleteAccountRecipeStatement.setInt(1, id);
                deleteAccountRecipeStatement.executeUpdate();

                // 最后删除recipe表中的记录
                String deleteRecipe = "DELETE FROM recipe WHERE id = ?";
                PreparedStatement deleteRecipeStatement = connection.prepareStatement(deleteRecipe);
                deleteRecipeStatement.setInt(1, id);
                deleteRecipeStatement.executeUpdate();

                // 提交事务
                connection.commit();
                success = true;
            } catch (SQLException e) {
                // 发生异常时回滚事务
                connection.rollback();
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return success;
    }
}
