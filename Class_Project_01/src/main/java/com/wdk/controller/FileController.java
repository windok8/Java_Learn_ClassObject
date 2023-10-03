package com.wdk.controller;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.read.listener.ReadListener;
import com.alibaba.excel.util.DateUtils;
import com.sun.org.apache.bcel.internal.generic.NEW;
import com.wdk.pojo.Account;
import com.wdk.pojo.DemoData;
import com.wdk.pojo.UserLevel;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.DateUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.*;

/**
 * @author : Windok
 * @date: 2023-10-03
 * @Description:
 * @version: 1.0
 */
@Slf4j
public class FileController {

    public void writeToExcel(){
        String fileName = "F:\\Dev_Demo\\Class_Project_Demo\\Class_Project_01\\src\\main\\resources\\database\\data.xlsx";
        List<Account> list = this.getList();
        EasyExcel.write(fileName, DemoData.class).sheet("模板").head(Account.class).doWrite(list);
    }

    public List<Account> readExcelToList() {
        InputStream inputStream = EasyExcel.class.getClassLoader().getResourceAsStream("database/data.xlsx");
        List<Account> lists = EasyExcel.read(inputStream).sheet().head(Account.class).doReadSync();
        for (Account item : lists) {
            log.info("读取到的数据：{}", item);
        }
        return lists;
    }

    @SneakyThrows
    private List<Account> getList() {
        Account account1 = new Account();
        account1.setUsername("test01");
        account1.setPassword("123456");
        account1.setUserLevel(UserLevel.SUPER_ADMIN);
        Account account2 = new Account();
        account2.setUsername("test02");
        account2.setPassword("a123456");
        account2.setUserLevel(UserLevel.ADMIN);
        Account account3 = new Account();
        account3.setUsername("test03");
        account3.setPassword("b123456");
        account3.setUserLevel(UserLevel.AUTHOR);
        Account account4 = new Account();
        account4.setUsername("test04");
        account4.setPassword("c123456");
        account4.setUserLevel(UserLevel.USER);

        return Arrays.asList(account1,account2,account3,account4);
    }

}
