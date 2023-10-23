package com.wdk.service.impl;

import com.wdk.dao.impl.CommentDaoImpl;
import com.wdk.dao.impl.CookBookDaoImpl;
import com.wdk.pojo.CookBook;
import com.wdk.pojo.RecipeStatus;
import com.wdk.service.CookBookService;

import java.util.Map;

/**
 * @author : Windok
 * @date: 2023-10-15
 * @Description:
 * @version: 1.0
 */
public class CookBookServiceImpl implements CookBookService {

    CookBookDaoImpl cookBookDao = new CookBookDaoImpl();
    CommentDaoImpl commitDao = new CommentDaoImpl();

    @Override
    public CookBook getcookBookById(int cookBookID) {
        CookBook cookBook = cookBookDao.getCookBookByRecipeID(cookBookID);
        cookBook.setComments(commitDao.getCommitsByRecipeID(cookBookID));
        return cookBook;
    }

    @Override
    public Map<Integer, RecipeStatus> getAccountAndRecipeByuID(int id) {
        return cookBookDao.getAccountAndRecipeByuID(id);
    }


    @Override
    public int modifyDescription(int id, String description) {
        cookBookDao.modifyRecipeStatus(id);
        if (cookBookDao.modifyContent(id, description, 2) == 1) return 1;
        return 0;
    }

    @Override
    public int deleteRecipe(int id) {
        if (cookBookDao.deleteRecipe(id)) return 1;
        return 0;
    }

    @Override
    public int modifyTitle(int id, String title) {
        cookBookDao.modifyRecipeStatus(id);
        if (cookBookDao.modifyContent(id, title, 1) == 1) return 1;
        return 0;
    }


}
