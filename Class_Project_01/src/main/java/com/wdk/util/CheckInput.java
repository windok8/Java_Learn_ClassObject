package com.wdk.util;

import com.wdk.pojo.GenderEnum;
import com.wdk.pojo.UserLevel;

import java.util.Scanner;

/**
 * @author : Windok
 * @date: 2023-10-03
 * @Description: 检查输入
 * @version: 1.0
 */
public class CheckInput {

    public static final String ERROR_MG = "【\u26A0】";
    public static final String ERROR_INPUT = "【\u26A0】输入错误，请重新输入！";
    public static final String IN_ENTER = "请输入您的选择：";
    private static String RE_ENTER = "请重新输入：";

    private String check_Input() {

        System.out.print(IN_ENTER);
        String input = new Scanner(System.in).nextLine();
        while (input.length() == 0) {
            System.out.println(ERROR_INPUT);
            System.out.print(RE_ENTER);
            input = new Scanner(System.in).nextLine();
        }
        return input;
    }

    /**
     * @param menu 菜单模块的编号
     * @return int  返回用户输入的值
     * @Author: Windok
     * @Description: 通过传入的菜单模块编号，检查用户输入的值是否符合当前菜单模块输入要求
     **/
    public int check_Menu_Input(String menu) {
        int result = 0;
        String input = check_Input();

        switch (menu) {
            case "menu0":
                result = check_Value_Input(input, '3');
                break;
            case "menu1":
                result = check_Value_Input(input, '4');
                break;
        }
        return result;
    }

    /**
     * @Author: Windok
     * @Description:    检查首页用户输入的值是否符合当前菜单模块输入要求
     * @param userLevel 用户等级
     * @return int
     **/
    public int check_Front_Page_Input(UserLevel userLevel) {
        int result = 0;

        String input = check_Input();

        switch (userLevel) {
            case GUEST:
                result = check_Value_Input(input, '3');
                break;
            case USER:
                result = check_Value_Input(input, '4');
                break;
            case AUTHOR:
                result = check_Value_Input(input, '4');
                break;
            case ADMIN:
            case SUPER_ADMIN:
                result = check_Value_Input(input, '4');
                break;
        }
        return result;
    }

    /**
     * @param input    用户输入的值
     * @param maxValue 当前菜单模块的最大值
     * @return int  返回用户输入的值
     * @Author: Windok
     * @Description: 检查用户输入的值是否符合当前菜单模块输入要求
     **/
    public int check_Value_Input(String input, char maxValue) {
        while (true) {
            try {
                if (input.length() != 1) {
                    throw new SystemException(ERROR_INPUT);
                }
                if (input.charAt(0) < '0' || input.charAt(0) > maxValue) {
                    throw new SystemException(ERROR_INPUT);
                }
                return Integer.parseInt(input);
            } catch (SystemException e) {
                System.out.println(e.getMessage());
                System.out.print(RE_ENTER);
                input = new Scanner(System.in).nextLine();
            }
        }

    }
    public int check_Value_Input_int(String input, int maxValue) {
        while (true) {
            try {
                if (input.length() == 0) {
                    throw new SystemException(ERROR_INPUT);
                }
                if (Integer.parseInt(input) < 0 || Integer.parseInt(input) > maxValue) {
                    throw new SystemException(ERROR_INPUT);
                }
                return Integer.parseInt(input);
            } catch (SystemException e) {
                System.out.println(e.getMessage());
                System.out.print(RE_ENTER);
                input = new Scanner(System.in).nextLine();
            }
        }
    }

    /**
     * @return String
     * @Author: Windok
     * @Description: 检查用户输入的邮箱是否符合要求
     **/
    public String check_Email_Input(String string) {
        String input = string;
        while (true) {
            try {
                if (input.length() == 0) {
                    throw new SystemException(ERROR_INPUT);
                }
                if (!input.matches("^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$")) {
                    throw new SystemException(ERROR_INPUT);
                }
                return input;
            } catch (SystemException e) {
                System.out.println(e.getMessage());
                System.out.print(RE_ENTER);
                input = new Scanner(System.in).nextLine();
            }
        }
    }

    /**
     * @return String
     * @Author: Windok
     * @Description: 检查用户输入的手机号是否符合要求
     **/
    public String check_Phone_Input(String string) {
        String input = string;
        while (true) {
            try {
                if (input.length() == 0) {
                    throw new SystemException(ERROR_INPUT);
                }
                if (!input.matches("^1[3-9]\\d{9}$")) {
                    throw new SystemException(ERROR_INPUT);
                }
                return input;
            } catch (SystemException e) {
                System.out.println(e.getMessage());
                System.out.print(RE_ENTER);
                input = new Scanner(System.in).nextLine();
            }
        }
    }

}
