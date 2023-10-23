package com.wdk.util;

import com.wdk.pojo.UserLevel;

/**
 * @author : Windok
 * @date: 2023-10-03
 * @Description: 菜单模块
 * @version: 1.0
 */
public class MenuModule {


    public static final String DELIMITER_1 = "--------------------------------------------------";
    public static final String DELIMITER_2 = "==================================================";
    public static final String DELIMITER_3 = "##################################################";

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
        waitTime = 2;
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
    public void start_Menu() {
        draw_Line(2);
        System.out.println("\t\t\t\t【美食之家】");
        System.out.println("\t\t\t\t【1】登录");
        System.out.println("\t\t\t\t【2】注册");
        System.out.println("\t\t\t\t【3】游览");
        System.out.println("\t\t\t\t【0】退出");
        draw_Line(2);
    }



    public void front_Page_Menu(UserLevel level){
        draw_Line(2);
        System.out.println("\t\t\t\t【首页】");
        System.out.println("\t\t\t\t【1】查看美食菜谱");
        if(level!=UserLevel.GUEST){
            System.out.println("\t\t\t\t【2】查看个人信息");
            if(level == UserLevel.AUTHOR){
                System.out.println("\t\t\t\t【3】查看我的菜谱");
                System.out.println("\t\t\t\t【4】发布菜谱");
            }
            if (level == UserLevel.ADMIN || level == UserLevel.SUPER_ADMIN){
                System.out.println("\t\t\t\t【3】查看所有菜谱信息");
                System.out.println("\t\t\t\t【4】查看所有用户信息");
            }
        }
        System.out.println("\t\t\t\t【0】登出");
        draw_Line(2);
    }

    /**
     * @Author: Windok
     * @Description: 用户个人信息菜单菜单
     **/
    public void userInfo_Menu(){
        draw_Line(2);
        System.out.println("\t\t\t\t【用户个人信息】");
        System.out.println("\t\t\t\t【1】查看个人信息");
        System.out.println("\t\t\t\t【2】修改个人信息");
        System.out.println("\t\t\t\t【3】修改密码");
        System.out.println("\t\t\t\t【4】注销账号");
        System.out.println("\t\t\t\t【0】返回上一级");
        draw_Line(2);
    }

    public void reviseUserInfo_Menu(){
        draw_Line(2);
        System.out.println("\t\t\t\t【修改个人信息】");
        System.out.println("\t\t\t\t【1】修改昵称");
        System.out.println("\t\t\t\t【2】修改性别");
        System.out.println("\t\t\t\t【3】修改邮箱");
        System.out.println("\t\t\t\t【4】修改手机号");
        System.out.println("\t\t\t\t【0】返回上一级");
        draw_Line(2);
    }

    public void recipePersonal_Menu(){
        draw_Line(2);
        System.out.println("\t\t\t\t【个人菜谱控制台】");
        System.out.println("\t\t\t\t【1】查看我的菜谱");
        System.out.println("\t\t\t\t【2】修改我的菜谱");
        System.out.println("\t\t\t\t【3】删除我的菜谱");
        System.out.println("\t\t\t\t【0】返回上一级");
        draw_Line(2);
    }

    public void reviseRecipe_Menu(){
        draw_Line(2);
        System.out.println("\t\t\t\t【菜谱信息修改】");
        System.out.println("\t\t\t\t【1】修改菜谱标题");
        System.out.println("\t\t\t\t【2】修改菜谱描述");
        System.out.println("\t\t\t\t【3】修改准备材料");
        System.out.println("\t\t\t\t【0】返回上一级");
        draw_Line(2);
    }



}
