package com.wdk.controller;

import com.wdk.pojo.Account;
import com.wdk.pojo.UserLevel;
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
        UserLevel level = account.getUserLevel();
        while (true) {
            menuModule.waitToMenu("首页");
            menuModule.front_Page_Menu(account.getUserLevel());
            int i = checkInput.check_Front_Page_Input(account.getUserLevel());
            switch (level){
                case GUEST:{
                    System.out.println("GUEST");
                    switch (i){
                        case 1:cookBookController.showAllCookBook();break;
                        case 0:startSystem();
                    }
                };break;
                case USER:{
                    System.out.println("USER");
                    switch (i){
                        case 1:cookBookController.showAllCookBook();break;
                        case 2:if (userController.userInfo(account.getId()) == 1) return;break;
                        case 0:startSystem();
                    }
                }break;
                case AUTHOR:{
                    System.out.println("AUTHOR");
                    switch (i){
                        case 1:cookBookController.showAllCookBook();break;
                        case 2:if (userController.userInfo(account.getId()) == 1) return;break;
                        case 3:{account = showMyCookBook(account);break;}
                        case 4:cookBookController.writeCookBook(account.getId());break;
                        case 0:startSystem();
                    }
                }break;
                case ADMIN:
                case SUPER_ADMIN:{
                    System.out.println("ADMIN|SUPER_ADMIN");
                    switch (i){
                        case 1:cookBookController.showAllCookBook();break;
                        case 2:if (userController.userInfo(account.getId()) == 1) return;break;
                        case 3:cookBookController.showAllCookBook_Admin();break;
                        case 4:accountController.showAllAccount();break;
                        case 0:startSystem();
                    }
                }break;

            }
        }
    }

    private Account showMyCookBook(Account account) throws InterruptedException {
        Account accountIsCook = cookBookController.showMyCookBook(account);
        if (accountIsCook == null) return account;
        account = accountIsCook;
        while (true) {
            menuModule.waitToMenu("个人菜谱控制台");
            menuModule.recipePersonal_Menu();
            System.out.print("请输入您的选择：");
            int i = checkInput.check_Value_Input(scanner.nextLine(), '3');
            switch (i) {
                case 1: {
                    cookBookController.showSelfRecipe(account);
                    System.out.println("查看菜谱【Over】");
                }
                break;
                case 2: {
                    account = cookBookController.reviseRecipe(account);
                    System.out.println("修改菜谱【Over】");
                }
                break;
                case 3: {
                    account = cookBookController.deleteRecipe(account);
                    System.out.println("删除菜谱【Over】");
                }
                break;
                case 0: {
                    return account;
                }
            }
        }


    }


}
