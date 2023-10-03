package com.wdk.util;

import java.util.Scanner;

/**
 * @author : Windok
 * @date: 2023-10-03
 * @Description:    检查输入
 * @version: 1.0
 */
public class CheckInput {

    private static String ERROR_INPUT = "【\u26A0】输入错误，请重新输入！";
    private static String IN_ENTER = "请输入您的选择：";
    private static String RE_ENTER = "请重新输入：";

    /**
     * @Author: Windok
     * @Description:    通过传入的菜单模块编号，检查用户输入的值是否符合当前菜单模块输入要求
     * @param menu  菜单模块的编号
     * @return int  返回用户输入的值
     **/
    public int check_Menu_Input(String menu)  {
        int result = 0;
        System.out.print(IN_ENTER);
        String input = new Scanner(System.in).nextLine();
        switch (menu) {
            case "menu0":
                result = check_Value_Input(input,'2');
                break;
        }
        return result;
    }

    /**
     * @Author: Windok
     * @Description:    检查用户输入的值是否符合当前菜单模块输入要求
     * @param input 用户输入的值
     * @param maxValue  当前菜单模块的最大值
     * @return int  返回用户输入的值
     **/
    private int check_Value_Input(String input,char maxValue)  {
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




}
