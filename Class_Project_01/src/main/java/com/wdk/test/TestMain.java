package com.wdk.test;

import com.wdk.controller.SystemController;

/**
 * @author : Windok
 * @date: 2023-10-10
 * @Description:
 * @version: 1.0
 */
public class TestMain {

    public static void main(String[] args) throws InterruptedException {

        SystemController systemController = new SystemController();
        systemController.startSystem();
        System.out.println("Hello World!");
    }

}
