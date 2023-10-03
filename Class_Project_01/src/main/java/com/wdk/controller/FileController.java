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

    @SneakyThrows
    private List<Account> getList() {
        Account account1 = new Account();
        account1.setUsername("张三");
        account1.setPassword("123456");
        account1.setUserLevel(UserLevel.USER);
        account1.setCreateTime(new Date());
        account1.setLastLoginTime(new Date());
        Account account2 = new Account();
        account2.setUsername("李四");
        account2.setPassword("123456");
        account2.setUserLevel(UserLevel.SUPER_ADMIN);
        account2.setCreateTime(DateUtils.parseDate("2023-09-14"));
        account2.setLastLoginTime(DateUtils.parseDate("2023-09-24"));
        Account account3 = new Account();
        account3.setUsername("王五");
        account3.setPassword("123456");
        account3.setUserLevel(UserLevel.ADMIN);
        account3.setCreateTime(DateUtils.parseDate("2023-10-01"));
        account3.setLastLoginTime(new Date());



        return Arrays.asList(account1, account2, account3);
    }

}
