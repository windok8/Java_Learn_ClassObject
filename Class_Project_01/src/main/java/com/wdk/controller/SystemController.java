package com.wdk.controller;

import com.wdk.pojo.Account;
import com.wdk.util.CheckInput;
import com.wdk.util.MenuModule;

import java.util.Scanner;

/**
 * @author : Windok
 * @date: 2023-10-10
 * @Description:
 * @version: 1.0
 */
public class SystemController {

    Scanner scanner = new Scanner(System.in);
    MenuModule menuModule = new MenuModule();
    CheckInput checkInput = new CheckInput();
    AccountController accountController = new AccountController();

    private static Boolean LOGIN_STATUS = false;

    static {
        System.out.println();
        System.out.println("欢迎来到美食之家");
        System.out.println();
    }

    public void startSystem() throws InterruptedException {

        while (true) {
            menuModule.waitToMenu("登录界面");
            menuModule.start_Menu();
            int i = checkInput.check_Menu_Input("menu0");
            switch (i) {
                //  登录
                case 1: {
                    if (LOGIN_STATUS) {
                        System.out.println();
                        System.out.println(menuModule.DELIMITER_1);
                        System.out.println("\t\t您已超过今日登录次数上限！");
                        System.out.println(menuModule.DELIMITER_1);
                        break;
                    }
                    Account account = accountController.login();
                    if (account == null) {
                        this.LOGIN_STATUS = true;
                        System.out.println();
                        System.out.println(menuModule.DELIMITER_1);
                        System.out.println("\t\t您已超过今日登录次数上限！");
                        System.out.println(menuModule.DELIMITER_1);
                        break;
                    }else {
                        this.LOGIN_STATUS = false;
                        frontPage(account);
                    }
                }
                break;
                //  注册
                case 2: {

                }
                //  游览
                case 3:
                    //  退出系统
                case 0: {
                    System.out.println();
                    System.out.println();
                    System.out.println("感谢您的使用，再见！");
                    System.exit(0);
                }
                break;
            }
        }

    }

    public void frontPage(Account account) throws InterruptedException {
        menuModule.waitToMenu("首页");
    }


}
