package com.wdk.service;

import com.wdk.pojo.Account;

import java.util.List;

/**
 * @author : Windok
 * @date: 2023-10-03
 * @Description:
 * @version: 1.0
 */
public interface AccountService {


    //  判断用户输入的密码是否正确
    boolean isAccountByPassword(int index,String password);
    void addAccount(Account account);


}
