package com.wdk.controller;

/**
 * @author : Windok
 * @date: 2023-10-03
 * @Description: 菜单模块
 * @version: 1.0
 */
public class MenuModule {

    private static String DELIMITER_1 = "--------------------------------------------------";
    private static String DELIMITER_2 = "==================================================";

    private void draw_Line(int i) {
        if (i == 1) System.out.println(DELIMITER_1);
        else if (i == 2) System.out.println(DELIMITER_2);
    }

    /**
     * @param where 跳转的目的地
     * @Author: Windok
     * @Description: 等待跳转
     **/
    public void waitToMenu(String where) throws InterruptedException {
        System.out.println();
        draw_Line(1);
        //  随机生成等待时间 [5-10]
        int waitTime = (int) (Math.random() * 5 + 5);
        System.out.println("正在前往【" + where + "】，请等待【" + waitTime + "】秒");
        for (int i = 0; i < waitTime; i++) {
            if (i < waitTime - 1) System.out.print("*\t");
            else {
                System.out.print("*\n");
            }
            Thread.sleep(1000);
        }
        draw_Line(1);
        System.out.println("\t\t\t\t【跳转成功!】");
        draw_Line(1);
        System.out.println();
    }


    /**
     * @Author: Windok
     * @Description: 主菜单
     **/
    public void main_Menu() {
        draw_Line(2);
        //  输出主菜单
        System.out.println("\t\t\t\t【美食之家】");
        System.out.println("\t\t\t\t【1】登录");
        System.out.println("\t\t\t\t【2】注册");
        System.out.println("\t\t\t\t【0】退出");
        draw_Line(2);
    }




}
