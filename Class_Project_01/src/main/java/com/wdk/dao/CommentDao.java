package com.wdk.dao;

import com.wdk.pojo.Comment;

import java.util.List;

/**
 * @author : Windok
 * @date: 2023-10-17
 * @Description:
 * @version: 1.0
 */
public interface CommentDao {
    List<Comment> getCommitsByRecipeID(int cookBookID);
}
