package com.wdk.test;

import com.wdk.controller.FileController;
import org.junit.jupiter.api.Test;

/**
 * @author : Windok
 * @date: 2023-10-03
 * @Description:
 * @version: 1.0
 */
public class FileTest {

    @Test
    public void testFileReadAccountToList(){
        TestFileDemo fileController = new TestFileDemo();
        fileController.fileReadAccountToList_3();
    }


}
