package com.wdk.controller;

import com.wdk.pojo.Account;
import com.wdk.pojo.UserLevel;
import com.wdk.util.CheckInput;
import com.wdk.util.DataHolder;
import com.wdk.util.MenuModule;

import java.text.SimpleDateFormat;

/**
 * @author : Windok
 * @date: 2023-10-10
 * @Description: 系统控制器
 * @version: 2.0
 */
public class SystemController {


    MenuModule menuModule = new MenuModule();
    CheckInput checkInput = new CheckInput();
    UserController userController = new UserController();


    //  静态代码块
    static {
        java.lang.System.out.println();
        java.lang.System.out.println("欢迎来到美食之家");
        java.lang.System.out.println();
    }

    private void over_To_Menu() throws InterruptedException {
        java.lang.System.out.println("请按回车键继续...");
        new java.util.Scanner(java.lang.System.in).nextLine();
        menuModule.waitToMenu("首页");
    }

    private void over_To_FrontPage() throws InterruptedException {
        java.lang.System.out.println("请按回车键继续...");
        Account account = UserController.account_Now;
        new java.util.Scanner(java.lang.System.in).nextLine();
        frontPage(account);
    }

    public void firstStart() {
        System.out.println("数据开始加载！");
        FileController fileController = new FileController();
        DataHolder dataHolder = new DataHolder();
        dataHolder.setAccountList(fileController.readExcelToList_Account());
        System.out.println("数据加载成功！");
    }

    public void startScreen() throws InterruptedException {
        menuModule.waitToMenu("登录页面");
        while (true) {
            menuModule.login_Menu();
            int result = checkInput.check_Menu_Input("menu0");
            switch (result) {
                //  登录
                case 1: {
                    userController.login();
                    over_To_FrontPage();
                }
                break;
                //  注册
                case 2: {
                    userController.register();
                    over_To_FrontPage();
                }
                break;
                // 游客
                case 3:
                    touristsPage();
                    over_To_FrontPage();
                    break;
                //  退出
                case 0:
                    //  TODO:   退出程序
                    System.out.println("感谢您的使用，再见！");
                    System.exit(0);
                    break;
            }
        }

    }


    public void touristsPage() {
        System.out.println("欢迎您，游客！");
        userController.tourists();
    }


    public void frontPage(Account account) throws InterruptedException {
        menuModule.waitToMenu("首页");
        System.out.println("欢迎您，" + account.getUser().getRealName() + "！");
        while (true){
            menuModule.front_Page_Menu(account.getUserLevel());
            if (account == null) {
                int result = checkInput.check_Front_Menu_Input(UserLevel.GUEST);
                frontPage_Into(UserLevel.GUEST, result);
            } else {
                int result = checkInput.check_Front_Menu_Input(account.getUserLevel());
                frontPage_Into(account.getUserLevel(), result);
            }
        }

    }

    public void frontPage_Into(UserLevel level, int input) throws InterruptedException {
        //  查看美食菜谱
        if (input == 1) return;
            //  查看个人信息
        else if (input == 0) startScreen();
            //  查看个人信息
        else if (input == 2 && level != UserLevel.GUEST) view_info();
        else if (level == UserLevel.AUTHOR) {
            //  查看我的菜谱
            if (input == 3) return;
            //  发布菜谱
            if (input == 4) return;
        } else if (level == UserLevel.ADMIN || level == UserLevel.SUPER_ADMIN) {
            //  查看所有菜谱
            if (input == 3) return;
            //  查看所有用户
            if (input == 4) return;
        }
    }

    public void view_info() throws InterruptedException {
        menuModule.waitToMenu("个人信息");
        menuModule.userInfo_Menu();
        int result = checkInput.check_Menu_Input("menu1");
        switch (result) {
            //  查看个人信息
            case 1: {
                userController.show_User_Info();
                System.out.println("请确认您的信息：[1->确认返回首页|0->前往修改]");
                if (checkInput.check_Value_Input(new java.util.Scanner(java.lang.System.in).nextLine(), '1') == 1) {
                    frontPage(UserController.account_Now);
                } else {
                    userController.update_User_Info();
                }
            }
            break;
            //  修改个人信息
            case 2:
                userController.update_User_Info();
                break;
            //  修改密码
            case 3:
                userController.update_User_Password();
                break;
            case 4:
                userController.delete_User();
                break;
            //  返回首页
            case 0:
                frontPage(UserController.account_Now);
                break;
        }

    }


}
