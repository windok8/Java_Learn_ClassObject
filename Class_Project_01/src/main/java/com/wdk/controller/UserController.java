package com.wdk.controller;

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

    public void login() throws InterruptedException {

        menuModule.waitToMenu("登录");
        System.out.print("请输入用户名：");
        String username = scanner.next();
        System.out.print("请输入密码：");
        String password = scanner.next();

        System.out.println("登录成功！");

    }

}
