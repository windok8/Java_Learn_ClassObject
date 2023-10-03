package com.wdk.test;

import com.wdk.controller.FileController;
import com.wdk.controller.SystemController;
import org.junit.Test;

/**
 * @author : Windok
 * @date: 2023-10-03
 * @Description:
 * @version: 1.0
 */
public class MainTest {

    @Test
    public void test_Front_Page() throws InterruptedException {
        SystemController systemController = new SystemController();
        systemController.startScreen();
    }

    @Test
    public void test_Excel() throws InterruptedException {
        FileController fileController = new FileController();
        fileController.fileReadAccountToList();
    }

}
