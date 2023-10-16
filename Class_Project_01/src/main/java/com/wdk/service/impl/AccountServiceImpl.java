package com.wdk.service.impl;

import com.wdk.dao.AccountDao;
import com.wdk.dao.impl.AccountDaoImpl;
import com.wdk.dao.impl.UserDaoImpl;
import com.wdk.pojo.Account;
import com.wdk.pojo.User;
import com.wdk.service.AccountService;

/**
 * @author : Windok
 * @date: 2023-10-10
 * @Description:
 * @version: 1.0
 */
public class AccountServiceImpl implements AccountService {

    AccountDaoImpl accountDao = new AccountDaoImpl();
    UserDaoImpl userDao = new UserDaoImpl();

    @Override
    public int checkAccount(String username, String password) {
        return accountDao.checkAccount(username, password);
    }

    @Override
    public Account getAccountById(int id) {
        Account account = accountDao.getAccountById(id);
        account.setUser(userDao.getUserById(id));
        return account;
    }

    @Override
    public boolean getAccountByUsername(String username) {
        return false;
    }
}
