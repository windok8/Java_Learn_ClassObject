package com.wdk.controller;

import com.wdk.util.CheckInput;

/**
 * @author : Windok
 * @date: 2023-10-03
 * @Description:    系统控制器
 * @version: 1.0
 */
public class SystemController {

    MenuModule menuModule = new MenuModule();
    CheckInput checkInput = new CheckInput();

    //  静态代码块
    static {
        java.lang.System.out.println();
        java.lang.System.out.println("欢迎来到美食之家");
        java.lang.System.out.println();
    }

    public void startScreen() throws InterruptedException {
        menuModule.waitToMenu("首页");
        menuModule.main_Menu();
        int result = checkInput.check_Menu_Input("menu0");
        switch (result){
            case 1:
            case 2:
            case 0:
                System.out.println("感谢您的使用，再见！");
                System.exit(0);
                break;
        }
    }



}
