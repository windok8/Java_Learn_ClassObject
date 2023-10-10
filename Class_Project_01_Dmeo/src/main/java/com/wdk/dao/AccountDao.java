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

    /**
     * @Author: Windok
     * @Description:    向数据库中添加账户信息
     * @param account   账户对象
     * @return int      返回影响的行数
     **/
    int addAccount(Account account);

    int deleteAccount(int id);

    int updateAccount(Account account);

    List<Account> findAllAccount();


}
