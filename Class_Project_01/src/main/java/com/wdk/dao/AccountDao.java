package com.wdk.dao;

import com.wdk.pojo.Account;

/**
 * @author : Windok
 * @date: 2023-10-03
 * @Description:
 * @version: 1.0
 */
public interface AccountDao {



    //  根据账户信息在集合的索引获取 Account 对象
    Account getAccountByIndex(int index);

    void addAccount(Account account);
}
