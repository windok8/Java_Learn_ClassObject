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

    private static String ERROR_ICON = "【\u26A0】";
    Scanner scanner = new Scanner(System.in);
    MenuModule menuModule = new MenuModule();
    SystemController systemController = new SystemController();
    AccountServiceImpl accountService = new AccountServiceImpl();

    public void login() throws InterruptedException {
        int oppty = 3;
        menuModule.waitToMenu("登录页面");
        System.out.print("请输入用户名：");
        String username = scanner.next();
        System.out.print("请输入密码：");
        String password = scanner.next();
        int index = 0;
        while ((index = DataHolder.getUserNameList().indexOf(username)) == -1) {
            System.out.println("用户名不存在，请重新输入！");
            System.out.print("请输入用户名：");
            username = scanner.next();
        }
        while (!accountService.isAccountByPassword(index, password)) {
            if (oppty == 0) {
                System.out.println(ERROR_ICON + "密码错误，您已经没有机会了！");
                //  TODO:   退出程序
                System.out.println("感谢您的使用，再见！");
                System.exit(0);
            }
            System.out.println(ERROR_ICON + "密码错误，还有【" + (oppty--) + "】次机会！");
            System.out.print("请输入密码：");
            password = scanner.next();
        }
        //  TODO:   登录成功后 需要进行获取 输出用户信息
        System.out.println("登录成功！");
        systemController.frontPage();

    }

    public void register() throws InterruptedException {
        menuModule.waitToMenu("注册页面");
        System.out.print("请输入用户名：");
        String username = scanner.next();
        while (DataHolder.getUserNameList().contains(username)) {
            System.out.println("用户名已存在，请重新输入！");
            System.out.print("请输入用户名：");
            username = scanner.next();
        }
        System.out.print("请输入密码：");
        String password = scanner.next();
        System.out.print("请再次输入密码：");
        while (true) {
            String password2 = scanner.next();
            if (password.equals(password2)) {
                break;
            } else {
                System.out.println("两次密码不一致，请重新注册！");
                System.out.print("请再次输入密码：");
            }
        }

        System.out.println("请继续完善个人信息：");

    }

}
