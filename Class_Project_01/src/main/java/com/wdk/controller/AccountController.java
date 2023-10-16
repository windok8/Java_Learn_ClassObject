package com.wdk.controller;

import com.wdk.pojo.Account;
import com.wdk.pojo.GenderEnum;
import com.wdk.pojo.User;
import com.wdk.pojo.UserLevel;
import com.wdk.service.impl.AccountServiceImpl;
import com.wdk.util.CheckInput;
import com.wdk.util.MenuModule;

import java.util.Scanner;

/**
 * @author : Windok
 * @date: 2023-10-10
 * @Description:
 * @version: 1.0
 */
public class AccountController {

    Scanner scanner = new Scanner(System.in);
    AccountServiceImpl accountService = new AccountServiceImpl();

    CheckInput checkInput = new CheckInput();

    public Account login() {
        String userName = null;
        String userPassword = null;
        int account_ID;
        for (int index = 0; index <= 3; index++) {
            System.out.print("请输入用户名：");
            userName = scanner.nextLine();
            System.out.print("请输入密码：");
            userPassword = scanner.nextLine();
            account_ID = accountService.checkAccount(userName, userPassword);
            if (account_ID != -1) {
                System.out.println("IF != 1" + account_ID);
                return accountService.getAccountById(account_ID);
            }
            System.out.println("【\u26A0】用户名或密码错误，请重新输入！您还有【" + (3 - index) + "】次机会！");
        }
        System.out.println(MenuModule.DELIMITER_1);
        System.out.println("您已输错3次，没有机会了！");
        System.out.println("【\u26A0】您已输错3次，没有机会了！");
        System.out.println(MenuModule.DELIMITER_1);
        return null;
    }


    public Account register() {
        String userName = null;
        String userPassword = null;
        Account account = new Account();
        while (true) {
            System.out.println("\t\t\t请输入用户名和密码：");
            System.out.println();

            userName = getUserName();
            System.out.println("用户名可用！");

            userPassword = getPassword();

            System.out.println(MenuModule.DELIMITER_1);
            System.out.println("\t\t请确认您的账号信息：");
            System.out.println("\t用户名：" + userName);
            System.out.println("\t密码：" + userPassword);
            System.out.println(MenuModule.DELIMITER_1);
            System.out.println("\t\t是否确认注册？");
            System.out.println("\t1.确认注册");
            System.out.println("\t2.重新输入");
            System.out.println("\t0.返回上一级");
            System.out.println(MenuModule.DELIMITER_1);
            System.out.print("请输入您的选择：");

            String input = scanner.nextLine();
            int i = checkInput.check_Value_Input(input, '2');
            if (i == 0) {
                System.out.println("取消注册");
                return null;
            } else if (i == 1) {
                account.setUsername(userName);
                account.setPassword(userPassword);
                System.out.println("注册成功！");
                System.out.println();
                System.out.println("请牢记您的账号信息!");
                break;
            }
        }
        account.setUser(writeUserInfo(account));
        return account;
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

    private String getUserName() {
        String username;
        do {
            System.out.print("请输入用户名：");
            username = scanner.nextLine().trim();
            if (accountService.getAccountByUsername(username) != false) {
                System.out.println("用户名已存在，请重新输入！");
            }
        } while (accountService.getAccountByUsername(username) != false);
        return username;
    }

    private User writeUserInfo(Account account) {
        String userName = null;
        String realNmae = null;
        GenderEnum genderEnum = null;
        String email = null;
        String phone = null;

        while (true) {

            System.out.println("\t\t\t请输入个人信息：");
            System.out.println();
            System.out.println("请输入您的昵称：");
            userName = scanner.nextLine();
            //  TODO:   检查用户名是否可用
            System.out.print("请输入您的真实姓名：");
            realNmae = scanner.nextLine();
            genderEnum = getGenderEnum();
            System.out.print("请输入您的电子邮箱：");
            email = checkInput.check_Email_Input(scanner.nextLine());
            System.out.print("请输入您的联系方式：");
            phone = checkInput.check_Phone_Input(scanner.nextLine());
            System.out.println(MenuModule.DELIMITER_1);
            System.out.println("\t\t请确认您的个人信息：");
            System.out.println("\t用户昵称：" + userName);
            System.out.println("\t真实姓名：" + realNmae);
            System.out.println("\t用户性别：" + genderEnum.getDescription());
            System.out.println("\t电子邮箱：" + email);
            System.out.println("\t联系方式：" + phone);
            System.out.println(MenuModule.DELIMITER_1);
            System.out.println("\t\t是否确认信息？");
            System.out.println("\t1.确认注册");
            System.out.println("\t2.重新输入");
            System.out.println("\t0.退出修改");
            System.out.println(MenuModule.DELIMITER_1);
            System.out.print("请输入您的选择：");

            String input = scanner.nextLine();
            int i = checkInput.check_Value_Input(input, '2');
            if (i == 0) {
                System.out.println("取消注册");
                return null;
            } else if (i == 1) {
                account.getUser().setRealName(realNmae);
                account.getUser().setGender(genderEnum);
                account.getUser().setEmail(email);
                account.getUser().setPhone(phone);
                System.out.println("个人信息修改成功！");
                System.out.println();
                break;
            }
        }

        return account.getUser();
    }

    private GenderEnum getGenderEnum() {
        GenderEnum genderEnum = null;
        System.out.println("请输入您的性别：【1 - 男|2 - 女|0 - 保密】");
        String input = scanner.nextLine();
        int i = checkInput.check_Value_Input(input, '2');
        switch (i) {
            case 0:
                genderEnum = GenderEnum.UNKNOWN;
                break;
            case 1:
                genderEnum = GenderEnum.MAN;
                break;
            case 2:
                genderEnum = GenderEnum.WOMAN;
                break;
        }
        return genderEnum;
    }


    public Account visitor_register() {
        Account account = new Account();
        account.setUserLevel(UserLevel.GUEST);
        account.setUser(writeVisitorInfo(account));
        return account;
    }

    private User writeVisitorInfo(Account account) {
        String email = null;
        String phone = null;
        while (true) {
            System.out.println("\t\t\t请输入游客信息：");
            System.out.println();
            System.out.print("请输入您的电子邮箱：");
            email = checkInput.check_Email_Input(scanner.nextLine());
            System.out.print("请输入您的联系方式：");
            phone = checkInput.check_Phone_Input(scanner.nextLine());
            System.out.println(MenuModule.DELIMITER_1);
            System.out.println("\t\t请确认您的个人信息：");
            System.out.println("\t电子邮箱：" + email);
            System.out.println("\t联系方式：" + phone);
            System.out.println(MenuModule.DELIMITER_1);
            System.out.println("\t\t是否确认游客信息？");
            System.out.println("\t1.确认信息");
            System.out.println("\t2.重新输入");
            System.out.println("\t0.返回上一级");
            System.out.println(MenuModule.DELIMITER_1);
            System.out.print("请输入您的选择：");

            String input = scanner.nextLine();
            int i = checkInput.check_Value_Input(input, '2');
            if (i == 0) {
                System.out.println("取消信息录入");
                return null;
            } else if (i == 1) {
                account.getUser().setEmail(email);
                account.getUser().setPhone(phone);
                System.out.println("游客信息修改成功！");
                System.out.println();
                break;
            }
        }

        return account.getUser();
    }

    public void showAllAccount() {

    }
}
