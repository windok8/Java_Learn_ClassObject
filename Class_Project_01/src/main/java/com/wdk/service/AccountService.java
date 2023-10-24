package com.wdk.service;

import com.wdk.pojo.Account;

import java.util.List;

/**
 * @author : Windok
 * @date: 2023-10-10
 * @Description:
 * @version: 1.0
 */
public interface AccountService {

    /**
     * @Author: Windok
     * @Description:    根据用户名和密码获取账户 id
     * @param username  用户名
     * @param password  密码
     * @return int      返回账户 id 如果不存在则返回 -1
     **/
    int checkAccount(String username, String password);
    /**
     * @Author: Windok
     * @Description:    根据账户 id 获取账户信息
     * @param id        账户 id
     * @return Account  返回账户信息
     **/
    Account getAccountById(int id);

    boolean getAccountByUsername(String username);

    void insertAccount(Account account);
    int updatePassword(int accountID, String newPassword);

    List<Account> getAllAccount();
}
