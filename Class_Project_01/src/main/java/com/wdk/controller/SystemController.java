package com.wdk.controller;

import com.wdk.util.CheckInput;
import com.wdk.util.DataHolder;
import com.wdk.util.MenuModule;

/**
 * @author : Windok
 * @date: 2023-10-03
 * @Description: 系统控制器
 * @version: 1.0
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

    public void firstStart() {
        FileController fileController = new FileController();
        DataHolder dataHolder = new DataHolder();
        dataHolder.setAccountList(fileController.readExcelToList_Account());
    }

    public void startScreen() throws InterruptedException {
        menuModule.waitToMenu("登录页面");
        menuModule.login_Menu();
        int result = checkInput.check_Menu_Input("menu0");
        switch (result) {
            //  登录
            case 1:
                userController.login();
                break;
            //  注册
            case 2:
                userController.register();
                break;
            // 游客
            case 3:
                break;
            //  退出
            case 0:
                //  TODO:   退出程序
                System.out.println("感谢您的使用，再见！");
                System.exit(0);
                break;
        }
    }

    public void frontPage() throws InterruptedException {
        menuModule.waitToMenu("首页");
        menuModule.front_Page_Menu();
        int result = checkInput.check_Menu_Input("menu1");
        switch (result) {
            case 1:
        }
    }


}
