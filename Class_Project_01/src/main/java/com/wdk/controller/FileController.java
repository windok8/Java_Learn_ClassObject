package com.wdk.controller;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.wdk.pojo.Account;
import com.wdk.pojo.GenderEnum;
import com.wdk.pojo.User;
import com.wdk.pojo.UserLevel;
import com.wdk.util.DataHolder;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.io.InputStream;
import java.util.*;

/**
 * @author : Windok
 * @date: 2023-10-03
 * @Description:
 * @version: 1.0
 */
@Slf4j
public class FileController {

    public void write_Account_To_Excel() {
        String fileName = "F:\\Dev_Demo\\Class_Project_Demo\\Class_Project_01\\src\\main\\resources\\database\\data.xlsx";
        List<Account> list = DataHolder.getAccountList();
        EasyExcel.write(fileName, Account.class).sheet(0,"账户信息").head(Account.class).doWrite(list);
    }

    public void write_User_To_Excel() {
        String fileName = "F:\\Dev_Demo\\Class_Project_Demo\\Class_Project_01\\src\\main\\resources\\database\\data.xlsx";
        List<User> list = DataHolder.getUserList();
        EasyExcel.write(fileName, User.class).sheet(1,"用户信息").head(User.class).doWrite(list);
    }


    public List<Account> readExcelToList_Account() {
        InputStream inputStream = EasyExcel.class.getClassLoader().getResourceAsStream("database/data.xlsx");
        List<Account> lists = EasyExcel.read(inputStream).sheet(0).head(Account.class).doReadSync();
        for (Account item : lists) {
            log.info("读取到的数据：{}", item);
        }
        return lists;
    }
    public List<User> readExcelToList_User() {
        InputStream inputStream = EasyExcel.class.getClassLoader().getResourceAsStream("database/data.xlsx");
        List<User> lists = EasyExcel.read(inputStream).sheet(1).head(User.class).doReadSync();
        for (User item : lists) {
            log.info("读取到的数据：{}", item);
        }
        return lists;
    }


}
