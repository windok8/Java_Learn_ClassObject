package com.wdk.pojo;

import com.alibaba.excel.annotation.ExcelProperty;

/**
 * @author : Windok
 * @date: 2023-10-03
 * @Description:
 * @version: 1.0
 */
public class User {

    @ExcelProperty(value = "真实姓名")
    private String realName;
    //  性别
    private Gender gender;
    @ExcelProperty(value = "手机号")
    private String email;
    @ExcelProperty(value = "邮箱")
    private String phone;

}
