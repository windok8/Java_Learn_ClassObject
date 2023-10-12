package com.wdk.pojo;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.DateTimeFormat;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.ContentStyle;
import com.wdk.util.UniqueIDGenerator;
import com.wdk.util.UserLevelConverter;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * @author : Windok
 * @date: 2023-10-03
 * @Description:
 * @version: 1.0
 */
@Data
@ContentStyle(fillForegroundColor = 10)
public class Account implements Serializable {

    @ExcelProperty(value = "用户ID")
    private Integer id;
    @ExcelProperty(value = "用户名")
    private String username;
    @ExcelProperty(value = "密码")
    private String password;
    @ColumnWidth(15)
    @ExcelProperty(value = "用户级别", converter = UserLevelConverter.class)
    private UserLevel userLevel;
    @ColumnWidth(23)
    @ExcelProperty(value = "创建时间")
    @DateTimeFormat(value = "yyyy年MM月dd HH:mm:ss")
    private Date createTime;
    @ColumnWidth(23)
    @ExcelProperty(value = "最后登录时间")
    @DateTimeFormat(value = "yyyy年MM月dd HH:mm:ss")
    private Date lastLoginTime;
    @ColumnWidth(23)
    @ExcelProperty(value = "菜谱数据")
    private List<Integer> cookIDList;
    @ExcelIgnore
    private User user;


    public Account() {
        this.id = (new Random().nextInt(999 - 100 + 1) + 100) * 1000 + UniqueIDGenerator.generateUniqueNumber();
        User user = new User();
        user.setUid(this.id);
        this.user = user;
        this.createTime = new Date();
        this.lastLoginTime = new Date();
    }

    public Account(Integer id, String username, String password, UserLevel userLevel, Date createTime, Date lastLoginTime) {
        this.id = (new Random().nextInt(999 - 100 + 1) + 100) * 1000 + UniqueIDGenerator.generateUniqueNumber();
        this.username = username;
        this.password = password;
        this.userLevel = userLevel;
        this.createTime = createTime;
        this.lastLoginTime = lastLoginTime;
    }
}
