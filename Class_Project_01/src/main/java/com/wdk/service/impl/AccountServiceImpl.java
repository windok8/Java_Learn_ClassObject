package com.wdk.service.impl;

import com.wdk.dao.AccountDao;
import com.wdk.dao.impl.AccountDaoImpl;
import com.wdk.dao.impl.CookBookDaoImpl;
import com.wdk.dao.impl.UserDaoImpl;
import com.wdk.pojo.Account;
import com.wdk.pojo.User;
import com.wdk.pojo.UserLevel;
import com.wdk.service.AccountService;

import java.util.List;
import java.util.Map;

/**
 * @author : Windok
 * @date: 2023-10-10
 * @Description:
 * @version: 1.0
 */
public class AccountServiceImpl implements AccountService {

    AccountDaoImpl accountDao = new AccountDaoImpl();
    UserDaoImpl userDao = new UserDaoImpl();
    CookBookServiceImpl cookBookService = new CookBookServiceImpl();

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

    @Override
    public void insertAccount(Account account) {
        accountDao.insertAccount(account);
        userDao.insertUser(account.getId(),account.getUser());
    }

    public int updatePassword(int accountID, String newPassword) {
        return accountDao.updatePassword(accountID, newPassword);
    }

    @Override
    public List<Account> getAllAccount() {
        return accountDao.getAllAccount();
    }

    public void deleteAccount(int accountID) {

    }
}
