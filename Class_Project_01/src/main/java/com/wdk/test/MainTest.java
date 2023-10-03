package com.wdk.test;

import com.wdk.controller.FileController;
import com.wdk.controller.SystemController;
import com.wdk.pojo.UserLevel;

/**
 * @author : Windok
 * @date: 2023-10-03
 * @Description:
 * @version: 1.0
 */
public class MainTest {


    public static void main(String[] args) throws InterruptedException {
        /*SystemController systemController = new SystemController();
        systemController.startScreen();*/
        FileController fileController = new FileController();
        fileController.writeToExcel();
        System.out.println("Hello World!");
    }

}
