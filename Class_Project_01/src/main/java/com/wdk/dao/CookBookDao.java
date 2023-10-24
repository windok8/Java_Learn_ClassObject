package com.wdk.dao;

import com.wdk.pojo.CookBook;
import com.wdk.pojo.RecipeStatus;

import java.util.List;
import java.util.Map;

/**
 * @author : Windok
 * @date: 2023-10-16
 * @Description:
 * @version: 1.0
 */
public interface CookBookDao {


    CookBook getCookBookByRecipeID(int id);


    Map<Integer, RecipeStatus> getAccountAndRecipeByuID(int id);


    int modifyRecipeStatus(int id);

    boolean deleteRecipe(int id);

    int modifyContent(int id, String content, int recipe_columns);

    List<CookBook> getRecipe();

    List<CookBook> getRecipe_Admin();
}
