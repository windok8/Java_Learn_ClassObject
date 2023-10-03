package com.wdk.controller;

/**
 * @author : Windok
 * @date: 2023-10-03
 * @Description:    菜单模块
 * @version: 1.0
 */
public class MenuModule {

    /**
     * @Author: Windok
     * @Description:    等待跳转
     * @param where 跳转的目的地
     **/
    public void waitToMenu(String where) throws InterruptedException {
        //  输出一条分割线
        java.lang.System.out.println("--------------------------------------------------");
        //  随机生成等待时间 [5-10]
        int waitTime = (int) (Math.random() * 5 + 5);
        java.lang.System.out.println("正在前往【"+where+"】，请等待【" + waitTime + "】秒");
        for (int i = 0; i < waitTime; i++) {
            if (i < waitTime - 1) java.lang.System.out.print("*\t");
            else {
                java.lang.System.out.print("*\n");
            }
            Thread.sleep(1000);
        }
        java.lang.System.out.println("--------------------------------------------------");
        java.lang.System.out.println("\t\t\t\t【跳转成功!】");
        java.lang.System.out.println("--------------------------------------------------");
        java.lang.System.out.println();
        java.lang.System.out.println();
        java.lang.System.out.println();
    }


    /**
     * @Author: Windok
     * @Description:    主菜单
     **/
    public void main_Menu() {
        //  输出一条 = 分割线
        java.lang.System.out.println("==================================================");
        //  输出主菜单
        java.lang.System.out.println("\t\t\t\t【美食之家】");
        java.lang.System.out.println("\t\t\t\t【1】登录");
        java.lang.System.out.println("\t\t\t\t【2】注册");
        java.lang.System.out.println("\t\t\t\t【0】退出");
        java.lang.System.out.println("==================================================");
    }





}
