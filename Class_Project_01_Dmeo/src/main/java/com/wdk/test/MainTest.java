package com.wdk.test;

import com.wdk.controller.SystemController;

/**
 * @author : Windok
 * @date: 2023-10-03
 * @Description:
 * @version: 1.0
 */
public class MainTest {


    public static void main(String[] args) throws Exception {

        SystemController systemController = new SystemController();
        systemController.firstStart();
        systemController.startScreen();


    }

}
