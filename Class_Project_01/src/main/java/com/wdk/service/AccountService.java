package com.wdk.service;

/**
 * @author : Windok
 * @date: 2023-10-03
 * @Description:
 * @version: 1.0
 */
public interface AccountService {

    //  根据用户名和密码查询用户
    public boolean login(String username, String password);

}
