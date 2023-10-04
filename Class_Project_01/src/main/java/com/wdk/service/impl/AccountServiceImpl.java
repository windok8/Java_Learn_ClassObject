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


    /**
     * @param index   用户名在集合中的索引
     * @param password  用户输入的密码
     * @return
     */
    @Override
    public boolean isAccountByPassword(int index, String password) {
        Account account = accountDao.getAccountByIndex(index);
        if(account.getPassword().equals(password)){
            return true;
        }
        return false;
    }


}
