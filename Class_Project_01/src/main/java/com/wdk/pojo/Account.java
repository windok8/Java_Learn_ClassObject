package com.wdk.pojo;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.DateTimeFormat;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.wdk.util.UserLevelConverter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.jupiter.params.shadow.com.univocity.parsers.annotations.Convert;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author : Windok
 * @date: 2023-10-03
 * @Description:
 * @version: 1.0
 */

@AllArgsConstructor
@Data
public class Account implements Serializable {

    @ExcelProperty(value = "用户ID")
    private Integer id;
    @ExcelProperty(value = "用户名")
    private String username;
    @ExcelProperty(value = "密码")
    private String password;
    @ColumnWidth(10)
    @ExcelProperty(value = "用户级别",converter = UserLevelConverter.class)
    private UserLevel userLevel;
    @ColumnWidth(23)
    @ExcelProperty(value = "创建时间")
    @DateTimeFormat(value = "yyyy年MM月dd HH:mm:ss")
    private Date createTime;
    @ColumnWidth(23)
    @ExcelProperty(value = "最后登录时间")
    @DateTimeFormat(value = "yyyy年MM月dd HH:mm:ss")
    private Date lastLoginTime;

    //  创建 Account 对象时，随机生成 id 值
    public Account(){
        this.id = (int) (Math.random() * 100000);
        this.createTime = new Date();
        this.userLevel = UserLevel.USER;
    }




}
