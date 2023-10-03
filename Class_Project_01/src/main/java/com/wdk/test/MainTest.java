package com.wdk.test;

import com.wdk.controller.FileController;
import com.wdk.controller.SystemController;
import com.wdk.dao.impl.AccountDaoImpl;
import com.wdk.pojo.Account;
import com.wdk.pojo.UserLevel;

import java.util.List;

/**
 * @author : Windok
 * @date: 2023-10-03
 * @Description:
 * @version: 1.0
 */
public class MainTest {


    public static void main(String[] args) throws InterruptedException {
        AccountDaoImpl accountDao = new AccountDaoImpl();
        List<Account> accounts = accountDao.getAccountList();
        for(Account account : accounts){
            System.out.println(account);
        }
        //  分割线
        System.out.println("====================================");
        accountDao.update();
        for(Account account : accounts){
            System.out.println(account);
        }

        System.out.println("Hello World!");
    }

}
