package com.wdk.service.impl;

import com.wdk.dao.AccountDao;
import com.wdk.dao.impl.AccountDaoImpl;
import com.wdk.pojo.Account;
import com.wdk.service.AccountService;

/**
 * @author : Windok
 * @date: 2023-10-10
 * @Description:
 * @version: 1.0
 */
public class AccountServiceImpl implements AccountService {

    AccountDaoImpl accountDao = new AccountDaoImpl();

    @Override
    public int checkAccount(String username, String password) {
        return -1;
    }

    @Override
    public Account getAccountById(int id) {
        return null;
    }

    @Override
    public boolean getAccountByUsername(String username) {
        return false;
    }
}
