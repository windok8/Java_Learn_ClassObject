package com.wdk.controller;

import com.wdk.pojo.Account;
import com.wdk.pojo.GenderEnum;
import com.wdk.service.impl.AccountServiceImpl;
import com.wdk.util.CheckInput;
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

    static Integer account_Index = null;
    static Account account_Now = null;
    private static String ERROR_ICON = "【\u26A0】";
    Scanner scanner = new Scanner(System.in);
    MenuModule menuModule = new MenuModule();
    AccountServiceImpl accountService = new AccountServiceImpl();
    CheckInput checkInput = new CheckInput();


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
        this.account_Index = index;
        this.account_Now = DataHolder.getAccountList().get(index);

    }

    public void register() throws InterruptedException {
        menuModule.waitToMenu("注册页面");
        String username = null;
        String password = null;
        while (true) {
            System.out.print("请输入用户名：");
            username = scanner.next();
            while (DataHolder.getUserNameList().contains(username)) {
                System.out.println("用户名已存在，请重新输入！");
                System.out.print("请输入用户名：");
                username = scanner.next();
            }
            System.out.print("请输入密码：");
            password = scanner.next();
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

            System.out.println("请确认您的账号信息：");
            System.out.println("用户名：" + username);
            System.out.println("密码：" + password);
            System.out.println("请确认您的信息：[1->确认|0->重新输入]");
            if (checkInput.check_Value_Input(scanner.next(), '1') == 1) {
                break;
            }
        }
        Account account = new Account();
        account.setUsername(username);
        account.setPassword(password);
        System.out.println();
        System.out.println("请继续完善个人信息：");
        System.out.println();

        String realName = null;
        GenderEnum genderEnum = null;
        String phone = null;
        String email = null;

        while (true) {
            System.out.print("请输入真实姓名：");
            account.getUser().setRealName(scanner.next());
            System.out.print("请输入您的性别：[0->保密|1->男|2->女]");
            //  检查输入是否为数字，且是否为1或2
            int i = checkInput.check_Value_Input(scanner.next(), '2');
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
            System.out.print("请输入您的手机号：");
            phone = checkInput.check_Phone_Input(scanner.nextLine());
            System.out.print("请输入您的邮箱：");
            email = checkInput.check_Email_Input(scanner.nextLine());
            System.out.println("请确认您的信息：");
            System.out.println("用户名：" + account.getUsername());
            System.out.println("密码：" + account.getPassword());
            System.out.println("真实姓名：" + account.getUser().getRealName());
            System.out.println("性别：" + account.getUser().getGender().getDescription());
            System.out.println("手机号：" + account.getUser().getPhone());
            System.out.println("邮箱：" + account.getUser().getEmail());
            System.out.println("请确认您的信息：[1->确认|0->重新输入]");
            if (checkInput.check_Value_Input(scanner.next(), '1') == 1) {
                break;
            }
        }
        account.getUser().setEmail(email);
        account.getUser().setEmail(email);
        account.getUser().setGender(genderEnum);
        account.getUser().setPhone(phone);
        account_Now = account;
        accountService.addAccount(account);
    }

}
