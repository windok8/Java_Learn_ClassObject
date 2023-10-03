package com.wdk.controller;

import com.wdk.pojo.Account;
import com.wdk.service.impl.AccountServiceImpl;
import com.wdk.util.DataHolder;
import com.wdk.util.MenuModule;

import java.util.Scanner;

/**
 * @author : Windok
 * @date: 2023-10-03
 * @Description:
 * @version: 1.0
 */
public class UserController {

    Scanner scanner = new Scanner(System.in);
    MenuModule menuModule = new MenuModule();
    AccountServiceImpl accountService = new AccountServiceImpl();

    public void login() throws InterruptedException {

        menuModule.waitToMenu("登录页面");
        System.out.print("请输入用户名：");
        String username = scanner.next();
        System.out.print("请输入密码：");
        String password = scanner.next();

        System.out.println("登录成功！");

    }

    public void register() throws InterruptedException {
        menuModule.waitToMenu("注册页面");
        System.out.print("请输入用户名：");
        String username = scanner.next();
        while (DataHolder.getUserNameList().contains(username)){
            System.out.println("用户名已存在，请重新输入！");
            System.out.print("请输入用户名：");
            username = scanner.next();
        };
        System.out.print("请输入密码：");
        String password = scanner.next();
        System.out.print("请再次输入密码：");
        while (true) {
            String password2 = scanner.next();
            if (password.equals(password2)) {
                System.out.println("注册成功！");
                break;
            } else {
                System.out.println("两次密码不一致，请重新注册！");
                System.out.print("请再次输入密码：");
            }
        }
        Account account = accountService.addAccount(username, password);
        System.out.println("请继续完善个人信息：");

    }

}
