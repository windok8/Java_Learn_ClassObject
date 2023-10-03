package com.wdk.dao.impl;

import com.wdk.controller.FileController;
import com.wdk.dao.AccountDao;
import com.wdk.pojo.Account;
import com.wdk.pojo.UserLevel;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @author : Windok
 * @date: 2023-10-03
 * @Description:
 * @version: 1.0
 */
@Data
public class AccountDaoImpl implements AccountDao {

    FileController fileController = new FileController();
    private List<Account> accountList = new FileController().readExcelToList();

    @Override
    public void writeAllData() {

    }

    @Override
    public void insert() {

    }

    @Override
    public void delete() {

    }

    @Override
    public void update() {
        accountList.add(new Account(null,"Test_New","n123456", UserLevel.USER,new Date(),new Date()));
    }
}
