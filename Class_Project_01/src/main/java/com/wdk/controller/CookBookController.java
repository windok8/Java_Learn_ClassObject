package com.wdk.controller;

import com.wdk.pojo.Account;
import com.wdk.pojo.CookBook;
import com.wdk.pojo.RecipeStatus;
import com.wdk.service.CookBookService;
import com.wdk.service.impl.AccountServiceImpl;
import com.wdk.service.impl.CookBookServiceImpl;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author : Windok
 * @date: 2023-10-15
 * @Description:
 * @version: 1.0
 */
public class CookBookController {

    AccountServiceImpl accountService = new AccountServiceImpl();
    CookBookServiceImpl cookBookService = new CookBookServiceImpl();
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy年MM月dd日");

    public Account showMyCookBook(Account account) {

        return null;
    }

    public void showAllCookBook() {

    }

    public void showAllCookBook_Admin() {

    }

    public void writeCookBook(Integer id) {

    }

    public Account getSelfCookBookIDs(Account account) {
        Map<Integer, List<String>> cookBookMaps = new HashMap<>();
        Map<Integer, RecipeStatus> cookBookMap = cookBookService.getAccountAndRecipeByuID(account.getId());
        if (cookBookMap == null) {
            account.setIsFindCookBook(true);
            return account;
        }
        List<Integer> cookBoook_IDs = new ArrayList<>();
        List<CookBook> cookBookList = new ArrayList<>();
        Set<Integer> cookBookIDs = cookBookMap.keySet();
        for (Integer cid : cookBookIDs) {
            cookBoook_IDs.add(cid);
            CookBook cookBook = cookBookService.getcookBookById(cid);
            cookBookList.add(cookBook);
            List<String> cookBookInfoStatus = new ArrayList<>();
            cookBookInfoStatus.add(cookBook.getTitle());
            cookBookInfoStatus.add(cookBookMap.get(cid).getDescription());
            cookBookInfoStatus.add(dateFormat.format(cookBook.getCreateTime()));
            cookBookMaps.put(cid, cookBookInfoStatus);
        }
        account.setCookBookIDs(cookBoook_IDs);
        account.setCookBooks(cookBookList);
        account.setCookBooksMap(cookBookMaps);
        account.setIsFindCookBook(true);
        return account;
    }
}
