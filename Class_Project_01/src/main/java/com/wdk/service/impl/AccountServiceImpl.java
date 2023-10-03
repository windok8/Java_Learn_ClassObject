package com.wdk.service.impl;

import com.wdk.dao.AccountDao;
import com.wdk.dao.impl.AccountDaoImpl;
import com.wdk.pojo.Account;
import com.wdk.service.AccountService;

import java.util.List;

/**
 * @author : Windok
 * @date: 2023-10-03
 * @Description:
 * @version: 1.0
 */
public class AccountServiceImpl implements AccountService {

    AccountDaoImpl accountDao = new AccountDaoImpl();

    @Override
    public Account addAccount(String userName, String password) {
        Account account = new Account();
        account.setUsername(userName);
        account.setPassword(password);

        return account;
    }
}
