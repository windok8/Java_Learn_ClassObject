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

    //  根据用户名和密码查询用户
    public boolean isExist(String username, String password);

    //  将多个用户信息写入到 Excel 文件中
    List<Account> writeToExcel();

}
