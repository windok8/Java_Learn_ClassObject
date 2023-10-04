package com.wdk.dao.impl;

import com.wdk.controller.FileController;
import com.wdk.dao.AccountDao;
import com.wdk.pojo.Account;
import com.wdk.pojo.UserLevel;
import com.wdk.util.DataHolder;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @author : Windok
 * @date: 2023-10-03
 * @Description:
 * @version: 1.0
 */
@Data
public class AccountDaoImpl implements AccountDao {

    @Override
    public Account getAccountByIndex(int index) {
        Account result = DataHolder.getAccountList().get(index);
        return result;
    }

    @Override
    public void addAccount(Account account) {
        List<Account> accountList = DataHolder.getAccountList();
        accountList.add(account);
        DataHolder.setAccountList(accountList);
    }

    @Override
    public void updataAccount(Account accountNow) {
        int accountIndex = DataHolder.getUidList().indexOf(accountNow.getId());
        if (DataHolder.getAccountList().get(accountIndex) != null) {
            DataHolder.getAccountList().set(accountIndex, accountNow);
        }
    }
}
