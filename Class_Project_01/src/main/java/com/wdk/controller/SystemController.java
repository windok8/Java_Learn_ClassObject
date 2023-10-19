package com.wdk.controller;

import com.wdk.pojo.Account;
import com.wdk.util.CheckInput;
import com.wdk.util.MenuModule;

import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Stack;

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
    UserController userController = new UserController();
    CookBookController cookBookController = new CookBookController();

    private static Boolean LOGIN_STATUS = false;
    private static Boolean REGISTER_STATUS = false;
    private static int REGISTER_NUMBER = 1;
    private static int LOGIN_NUMBER = 1;


    static {
        System.out.println();
        System.out.println("欢迎来到美食之家");
        System.out.println();
    }

    private void write_Limit(String errorIMG) {
        System.out.println();
        System.out.println(menuModule.DELIMITER_1);
        System.out.println("\t\t您已超过今日" + errorIMG + "次数上限！");
        System.out.println(menuModule.DELIMITER_1);
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
                        write_Limit("登录");
                        break;
                    }
                    Account account = accountController.login();
                    if (account == null) {
                        if (LOGIN_NUMBER++ > 3) {
                            this.LOGIN_STATUS = true;
                            write_Limit("登录");
                            break;
                        }
                        break;
                    } else {
                        this.LOGIN_STATUS = false;
                        frontPage(account);
                    }
                }
                break;
                //  注册
                case 2: {
                    if (REGISTER_NUMBER++ > 3) {
                        write_Limit("注册");
                        break;
                    }
                    Account registerAccount = accountController.register();
                    if (registerAccount != null) {
                        frontPage(registerAccount);
                    }
                    break;
                }
                //  游览
                case 3: {
                    frontPage(accountController.visitor_register());
                }
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
        while (true) {
            menuModule.waitToMenu("首页");
            menuModule.front_Page_Menu(account.getUserLevel());
            int i = checkInput.check_Front_Page_Input(account.getUserLevel());
            switch (i) {
                //  查看美食菜谱
                case 1: {
                    cookBookController.showAllCookBook();
                    System.out.println("查看美食菜谱");
                }
                break;
                //  查看个人信息
                case 2: {

                    if(userController.userInfo(account.getId()) == 1) return;
                    System.out.println("查看个人信息【Over】");
                }
                break;
                //  查看我的菜谱
                case 3: {
                    showMyCookBook(account);

                    System.out.println("查看我的菜谱【Over】");
                    frontPage(account);
                }
                break;
                //  发布菜谱
                case 4: {
                    cookBookController.writeCookBook(account.getId());
                    System.out.println("发布菜谱");
                }
                break;
                //  查看所有菜谱
                case 5: {
                    cookBookController.showAllCookBook_Admin();
                    System.out.println("查看所有菜谱信息");
                }
                break;
                //  查看所有用户
                case 6: {
                    accountController.showAllAccount();
                    System.out.println("查看所有用户");
                }
                break;
                //  登出
                case 0: {
                    System.out.println("登出");
                    startSystem();
                }
                break;
            }
        }
    }

    private void showMyCookBook(Account account) {
        if (account.getCookBookIDs() == null) {
            if (account.getIsFindCookBook()) {
                System.out.println("您还没有发布过菜谱哦！");
                return;
            }
            //  通过accountID获取自己的所有菜谱ID
            account = cookBookController.getSelfCookBookIDs(account);
            showMyCookBook(account);
        }
        List<Integer> cookBookIDs = account.getCookBookIDs();
        for (int i = 0; i < cookBookIDs.size(); i++) {
            System.out.println(
                    "菜谱名称：" + account.getCookBooksMap().get(cookBookIDs.get(i)).get(0) +
                    "\t\t菜谱状态：" + account.getCookBooksMap().get(cookBookIDs.get(i)).get(1) +
                    "\t\t创作时间：" + account.getCookBooksMap().get(cookBookIDs.get(i)).get(2));
        }
        //  任意按键继续
        System.out.println("任意按键继续");
        scanner.next();


    }


}
