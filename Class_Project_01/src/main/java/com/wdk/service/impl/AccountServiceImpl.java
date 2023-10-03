package com.wdk.service.impl;

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


    @Override
    public boolean isExist(String username, String password) {

        return false;
    }

    @Override
    public List<Account> writeToExcel() {
        return null;
    }
}
