package com.wdk.controller;

import com.wdk.pojo.Account;
import com.wdk.pojo.CookBook;
import com.wdk.service.CookBookService;
import com.wdk.service.impl.AccountServiceImpl;
import com.wdk.service.impl.CookBookServiceImpl;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : Windok
 * @date: 2023-10-15
 * @Description:
 * @version: 1.0
 */
public class CookBookController {

    AccountServiceImpl accountService = new AccountServiceImpl();
    CookBookServiceImpl cookBookService = new CookBookServiceImpl();

    public void showMyCookBook(Integer id) {
        List<CookBook> cookBookList = new ArrayList<>();
        Account account = accountService.getAccountById(id);
        for (int i = 0; i < account.getCookIDList().size(); i++) {
            System.out.println(account.getCookIDList().get(i));
            //cookBookList.add(cookBookService.getcookBookById(account.getCookIDList().get(i)));
        }
        if (cookBookList.size() == 0) {
            System.out.println("您还没有收藏任何菜谱！");
            return;
        }
        for (CookBook cookBook : cookBookList) {
            System.out.println(cookBook);
        }

    }

    public void showAllCookBook() {

    }

    public void showAllCookBook_Admin() {

    }

    public void writeCookBook(Integer id) {

    }
}
