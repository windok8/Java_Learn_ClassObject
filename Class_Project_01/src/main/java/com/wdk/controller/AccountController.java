package com.wdk.controller;

import com.wdk.pojo.Account;
import com.wdk.service.impl.AccountServiceImpl;
import com.wdk.util.MenuModule;

import java.util.Scanner;

/**
 * @author : Windok
 * @date: 2023-10-10
 * @Description:
 * @version: 1.0
 */
public class AccountController {

    static Boolean LOGIN_STATUS = false;

    Scanner scanner = new Scanner(System.in);
    AccountServiceImpl accountService = new AccountServiceImpl();

    public Account login() {
        System.out.println();
        //  提示用户输入用户名和密码
        System.out.println("\t\t\t请输入用户名和密码：");
        System.out.print("请输入用户名：");
        String username = scanner.nextLine();
        System.out.print("请输入密码：");
        String password = scanner.nextLine();
        //  验证用户名和密码是否正确
        int id = accountService.checkAccount(username, password);
        int index = 0;
        while (id == -1) {
            if(index == 3) {
                System.out.println( "您已输错3次，没有机会了！");
                System.out.println("【\u26A0】您已输错3次，没有机会了！");
                return null;
            }
            System.out.println("【\u26A0】用户名或密码错误，请重新输入！您还有【"+(3 - (index++))+"】次机会！");
            System.out.print("请输入用户名：");
            username = scanner.nextLine();
            System.out.print("请输入密码：");
            password = scanner.nextLine();
        }
        return accountService.getAccountById(id);
    }


}
