package com.wdk.controller;

import com.wdk.pojo.Account;
import com.wdk.pojo.GenderEnum;
import com.wdk.pojo.UserLevel;
import com.wdk.util.CheckInput;
import com.wdk.util.DataHolder;
import com.wdk.util.MenuModule;

import java.text.SimpleDateFormat;
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
    CheckInput checkInput = new CheckInput();

    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");


    public void login() throws InterruptedException {
        int oppty = 3;
        menuModule.waitToMenu("登录页面");
        System.out.println(ERROR_ICON + "请输入用户名和密码：");
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
        while (true) {
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
//        System.out.println("登录成功！");
//        this.account_Index = index;
//        this.account_Now = DataHolder.getAccountList().get(index);
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


        account = user_Info_Write(account);
        account_Now = account;
    }

    private Account user_Info_Write(Account account) {
        String realName = null;
        GenderEnum genderEnum = null;
        String phone = null;
        String email = null;
        while (true) {
            System.out.print("请输入真实姓名：");
            account.getUser().setRealName(scanner.next());
            System.out.print("请输入您的性别：[0->保密|1->男|2->女]：");
            //  检查输入是否为数字，且是否为1或2
            switch (checkInput.check_Value_Input(scanner.next(), '2')) {
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
            account.getUser().setGender(genderEnum);
            System.out.print("请输入您的手机号：");
            phone = checkInput.check_Phone_Input(scanner.next());
            account.getUser().setPhone(phone);
            System.out.print("请输入您的邮箱：");
            email = checkInput.check_Email_Input(scanner.next());
            account.getUser().setEmail(email);
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

        return account;
    }

    public void show_User_Info() throws InterruptedException {

        System.out.println();
        System.out.println("您的个人信息如下：");
        System.out.println("用户名：" + UserController.account_Now.getUsername());
        System.out.println("密码：" + UserController.account_Now.getPassword());
        System.out.println("真实姓名：" + UserController.account_Now.getUser().getRealName());
        System.out.println("性别：" + UserController.account_Now.getUser().getGender());
        System.out.println("手机号：" + UserController.account_Now.getUser().getPhone());
        System.out.println("邮箱：" + UserController.account_Now.getUser().getEmail());
        System.out.println("用户等级：" + UserController.account_Now.getUserLevel().getDescription());
        System.out.println("注册时间：" + dateFormat.format(UserController.account_Now.getCreateTime()));
        System.out.println();


    }

    public void update_User_Info() {
        account_Now = user_Info_Write(UserController.account_Now);
//        accountService.updataAccount(account_Now);
    }

    public void update_User_Password() {
        System.out.print("请输入原密码：");
        String password = scanner.next();
        while (!password.equals(UserController.account_Now.getPassword())) {
            System.out.println(ERROR_ICON + "密码错误，请重新输入！");
            System.out.print("请输入原密码：");
            password = scanner.next();
        }
        System.out.print("请输入新密码：");
        String password1 = scanner.next();
        System.out.print("请再次输入新密码：");
        while (true) {
            String password2 = scanner.next();
            if (password1.equals(password2)) {
                break;
            } else {
                System.out.println("两次密码不一致，请重新输入！");
                System.out.print("请再次输入新密码：");
            }
        }
        account_Now.setPassword(password1);
//        accountService.updataAccount(account_Now);
    }

    public void delete_User() {
        System.out.println("您确定要注销账号吗？[1->确认|0->取消]");
        if (checkInput.check_Value_Input(scanner.next(), '1') == 1) {
            DataHolder.getAccountList().remove(account_Index);
            System.out.println("账号已删除！");
            System.out.println("感谢您的使用，再见！");
            System.exit(0);
        }
    }


    public void tourists() {
        System.out.println("游客，该叫你什么好呢？");
        String nickname = null;
        String email = null;
        String phone = null;
        while (true) {
            System.out.print("请输入您的昵称：");
            nickname = scanner.next();
            System.out.print("请输入您的邮箱：");
            email = checkInput.check_Email_Input(scanner.next());
            System.out.print("请输入您的手机号：");
            phone =checkInput.check_Phone_Input(scanner.next());
            System.out.println("请确认您的信息：");
            System.out.println("昵称：" + nickname);
            System.out.println("邮箱：" + email);
            System.out.println("手机号：" + phone);
            System.out.println("请确认您的信息：[1->确认|0->重新输入]");
            if (checkInput.check_Value_Input(scanner.next(), '1') == 1) {
                break;
            }
        }
        Account account = new Account();
        account.getUser().setRealName(nickname);
        account.getUser().setEmail(email);
        account.getUser().setPhone(phone);
        account.setUserLevel(UserLevel.GUEST);
        account_Now = account;
    }
}
