package com.wdk.pojo;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.wdk.util.GenderConverter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author : Windok
 * @date: 2023-10-03
 * @Description:
 * @version: 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {

    @ExcelProperty(value = "用户ID")
    private Integer uid;
    @ExcelProperty(value = "真实姓名")
    private String realName;
    @ExcelProperty(value = "性别", converter = GenderConverter.class)
    private GenderEnum gender;
    @ExcelProperty(value = "手机号")
    private String email;
    @ExcelProperty(value = "邮箱")
    private String phone;



}
