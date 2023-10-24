package com.wdk.dao;

import com.wdk.pojo.Account;

import java.util.List;

/**
 * @author : Windok
 * @date: 2023-10-10
 * @Description:
 * @version: 1.0
 */
public interface AccountDao {


    int checkAccount(String username, String password);

    Account getAccountById(int id);

    int insertAccount(Account account);
    int updatePassword(int accountID, String newPassword);

    List<Account> getAllAccount();
}
