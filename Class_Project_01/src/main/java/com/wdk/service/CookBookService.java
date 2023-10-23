package com.wdk.service;

import com.wdk.pojo.CookBook;
import com.wdk.pojo.RecipeStatus;

import java.util.Map;

/**
 * @author : Windok
 * @date: 2023-10-15
 * @Description:
 * @version: 1.0
 */
public interface CookBookService {
    CookBook getcookBookById(int cookBookID);

    Map<Integer, RecipeStatus> getAccountAndRecipeByuID(int id);

    int deleteRecipe(int id);

    int modifyDescription(int id, String title);
    int modifyTitle(int id, String title);
}
