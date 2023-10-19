package com.wdk.controller;

import com.wdk.pojo.Account;
import com.wdk.service.AccountService;
import com.wdk.service.impl.AccountServiceImpl;
import com.wdk.util.CheckInput;
import com.wdk.util.MenuModule;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Scanner;

/**
 * @author : Windok
 * @date: 2023-10-15
 * @Description:
 * @version: 1.0
 */
public class UserController {

    Scanner scanner = new Scanner(System.in);
    MenuModule menuModule = new MenuModule();
    CheckInput checkInput = new CheckInput();
    AccountServiceImpl accountService = new AccountServiceImpl();
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy年MM月dd日");

    public int userInfo(int accountID) throws InterruptedException {

        while (true) {
            menuModule.waitToMenu("个人信息控制台");
            menuModule.userInfo_Menu();
            int i = checkInput.check_Menu_Input("menu1");
            switch (i) {
                //  查看个人信息
                case 1:
                    showUserInfo(accountID);
                    break;
                //  修改个人信息
                case 2:
                    reviseUserInfo(accountID);
                    break;
                //  修改密码
                case 3:
                    changePassword(accountID);
                    break;
                //  注销账号
                case 4:
                   if(logoutAccount(accountID)) return 1;
                    break ;
                //  返回上一级
                case 0: {
                    System.out.println();
                    return 0;
                }
            }
        }


    }

    private Boolean logoutAccount(int accountID) {
        //  注销账号
        System.out.println("您确定要注销账号吗？（Y/N）");
        String s = scanner.nextLine();
        if (s.equals("Y")) {
            accountService.deleteAccount(accountID);
            System.out.println("账号注销成功！");
            System.out.println("按任意键返回登录界面...");
            new Scanner(System.in).nextLine();
            System.out.println();
            System.out.println();
            return true;
        }
        return false;
    }

    private void changePassword(int accountID) {
        //  输入原密码
        System.out.print("请输入原密码：");
        String oldPassword = scanner.nextLine();
        String newPassword = getPassword();
        //  判断原密码是否正确
        Account account = accountService.getAccountById(accountID);
        if (!account.getPassword().equals(oldPassword)) {
            System.out.println("原密码错误！");
            return;
        }
        accountService.updatePassword(accountID, newPassword);
    }

    private String getPassword() {
        System.out.print("请输入密码：");
        String password = scanner.nextLine();
        System.out.print("请再次输入密码：");
        while (true) {
            String password2 = scanner.nextLine();
            if (password.equals(password2)) {
                return password;
            } else {
                System.out.println("两次密码不一致，请再次输入！");
                System.out.print("请再次输入密码：");
            }
        }
    }

    private void reviseUserInfo(int accountID) {
        Account account = accountService.getAccountById(accountID);
        displayUserInfo(account);
        System.out.println("请输入您要修改的信息编号：");
        int i = checkInput.check_Menu_Input("menu2");
    }

    private void displayUserInfo(Account account) {
        System.out.println();
        System.out.println(menuModule.DELIMITER_1);
        System.out.println("\t\t\t\t【个人信息】");
        System.out.println("\t\t\t\t用户昵称：" + account.getUser().getUserName());
        System.out.println("\t\t\t\t用户级别：" + account.getUserLevel().getDescription());
        System.out.println("\t\t\t\t用户性别：" + account.getUser().getGender().getDescription());
        System.out.println("\t\t\t\t用户邮箱：" + account.getUser().getEmail());
        System.out.println("\t\t\t\t联系方式：" + account.getUser().getPhone());
        System.out.println("\t\t\t\t注册时间：" + dateFormat.format(account.getCreateTime()));
        System.out.println("\t\t\t\t最后登录时间：" + dateFormat.format(account.getLastLoginTime()));
        System.out.println(menuModule.DELIMITER_1);
        System.out.println();
    }

    private void showUserInfo(int accountID) {
        Account account = accountService.getAccountById(accountID);
        displayUserInfo(account);
        //  按任意键继续
        System.out.println("按任意键继续...");
        new Scanner(System.in).nextLine();
    }
}
