package com.wdk.controller;

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

    public void login() throws InterruptedException {

        menuModule.waitToMenu("登录");
        System.out.print("请输入用户名：");
        String username = scanner.next();
        System.out.print("请输入密码：");
        String password = scanner.next();

        System.out.println("登录成功！");

    }

    public void register() throws InterruptedException {
        menuModule.waitToMenu("注册");
        System.out.print("请输入用户名：");
        //  TODO: 需要判断用户名是否已经存在
        String username = scanner.next();
        System.out.print("请输入密码：");
        String password = scanner.next();
        System.out.print("请再次输入密码：");
        String password2 = scanner.next();
        if (password.equals(password2)) {
            System.out.println("注册成功！");
        } else {
            System.out.println("两次密码不一致，请重新注册！");
            register();
        }
    }

}
