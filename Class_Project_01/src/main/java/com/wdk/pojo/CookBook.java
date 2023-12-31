package com.wdk.pojo;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.DateTimeFormat;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.ContentStyle;
import com.wdk.util.UniqueIDGenerator;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * @author : Windok
 * @date: 2023-10-03
 * @Description:
 * @version: 1.0
 */
@Data
@ContentStyle(fillForegroundColor = 10)
public class CookBook implements Serializable {

    @ExcelProperty(value = "菜品ID")
    private Integer cookID;
    @ExcelIgnore
    private Integer userID;
    @ExcelIgnore
    private String userEmail;
    @ExcelProperty(value = "菜谱标题")
    private String title;
    @ExcelIgnore
    private String authorName;
    @ExcelIgnore
    private String authorRealName;
    @ExcelProperty(value = "菜品描述")
    private Map<Integer,String> description;
    @ExcelProperty(value = "作品点赞")
    private Integer likes;
    @ColumnWidth(23)
    @ExcelProperty(value = "创作时间")
    @DateTimeFormat(value = "yyyy年MM月dd HH:mm:ss")
    private Date createTime;
    @ColumnWidth(23)
    @ExcelProperty(value = "更新时间")
    @DateTimeFormat(value = "yyyy年MM月dd HH:mm:ss")
    private Date updateTime;
    @ColumnWidth(50)
    @ExcelProperty(value = "准备材料")
    private Map<String,List<String>> materials;
    @ColumnWidth(50)
    @ExcelProperty(value = "制作步骤")
    private int steps;
    private RecipeStatus status;
    private List<Comment> comments;
    private int commentCount;

    //  创建 CookBook 对象时，自动生成一个 3 位数的 cookID 且首位不能为 0
    public CookBook() {
        this.cookID = (new Random().nextInt(999 - 100 + 1) + 100) * 1000 + UniqueIDGenerator.generateUniqueNumber();
        this.status = RecipeStatus.PENDING;
        this.createTime = new Date();
        this.updateTime = new Date();
    }

    public CookBook(Integer id){
        this.cookID = id;
    }


}
