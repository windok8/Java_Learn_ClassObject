package com.wdk.test;

import com.alibaba.excel.EasyExcel;
import com.wdk.pojo.DemoData;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

/**
 * @author : Windok
 * @date: 2023-10-03
 * @Description:
 * @version: 1.0
 */
@Slf4j
public class TestFileDemo {

    private static String PATH = "src/main/resources";

    public void fileReadAccountToList_1() {
        File file = new File(PATH + "/database/test01.xlsx");
        List<Object> objects = EasyExcel.read(file).sheet().doReadSync();
        for (Object object : objects) {
            log.info("读取到的数据类型：{}", object.getClass());
            log.info("读取到的数据：{}", object);

        }
    }

    public void fileReadAccountToList_2() {
        InputStream inputStream = EasyExcel.class.getClassLoader().getResourceAsStream("database/test01.xlsx");
        List<Map<Integer, Object>> lists = EasyExcel.read(inputStream).sheet().doReadSync();
        for (Map item : lists) {
            log.info("读取到的数据：字符串标题:{}\t日期标题:{}\t数字标题:{}", item.get(0), item.get(1), item.get(2));
        }
    }

    public void fileReadAccountToList_3() {
        InputStream inputStream = EasyExcel.class.getClassLoader().getResourceAsStream("database/test01.xlsx");
        List<DemoData> lists = EasyExcel.read(inputStream).sheet().head(DemoData.class).doReadSync();
        for (DemoData item : lists) {
            log.info("读取到的数据：字符串标题:{}\t日期标题:{}\t数字标题:{}",item.getString(),item.getDate(),item.getDoubleData());
        }
    }

}
