package com.wdk.pojo;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author : Windok
 * @date: 2023-10-03
 * @Description:
 * @version: 1.0
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Account implements Serializable {

    @ExcelProperty("用户ID")
    private Integer id;
    @ExcelProperty("用户名")
    private String username;
    @ExcelProperty("密码")
    private String password;
    @ExcelProperty("用户级别")
    private UserLevel userLevel;
    @ExcelProperty("创建时间")
    private Date createTime;
    @ExcelProperty("最后登录时间")
    private Date lastLoginTime;

    //  创建 Account 对象时，随机生成 id 值
    public Account(String username, String password) {
        this.id = (int) (Math.random() * 100000);
        this.username = username;
        this.password = password;
        this.userLevel = UserLevel.USER;
        this.createTime = new Date();
        this.lastLoginTime = new Date();
    }


}
