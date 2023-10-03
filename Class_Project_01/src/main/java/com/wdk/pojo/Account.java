package com.wdk.pojo;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.DateTimeFormat;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.ContentStyle;
import com.wdk.util.DataHolder;
import com.wdk.util.UserLevelConverter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.jupiter.params.shadow.com.univocity.parsers.annotations.Convert;

import java.io.Serializable;
import java.text.SimpleDateFormat;
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
        int randomNumber =new Random().nextInt(999999 - 100000 + 1) + 100000;
        while (DataHolder.getUidList().contains(randomNumber)) {
            randomNumber =new Random().nextInt(999999 - 100000 + 1) + 100000;
        }
        this.id = randomNumber;
        this.user.setUid(this.id);
        this.createTime = new Date();
        this.lastLoginTime = new Date();
        this.userLevel = UserLevel.USER;
    }

    public Account(Integer id, String username, String password, UserLevel userLevel, Date createTime, Date lastLoginTime) {
        int randomNumber =new Random().nextInt(999999 - 100000 + 1) + 100000;
        while (DataHolder.getUidList().contains(randomNumber)) {
            randomNumber =new Random().nextInt(999999 - 100000 + 1) + 100000;
        }
        this.id = randomNumber;
        this.username = username;
        this.password = password;
        this.userLevel = userLevel;
        this.createTime = createTime;
        this.lastLoginTime = lastLoginTime;
    }
}
