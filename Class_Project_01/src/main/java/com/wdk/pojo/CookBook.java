package com.wdk.pojo;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.DateTimeFormat;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.ContentStyle;
import com.wdk.util.UniqueIDGenerator;
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
public class CookBook implements Serializable {

    @ExcelProperty(value = "菜品ID")
    private Integer cookID;
    @ExcelProperty(value = "菜谱标题")
    private String title;
    @ExcelProperty(value = "菜谱作者ID")
    private Integer uid;
    @ExcelProperty(value = "菜谱作者")
    private String author;
    @ExcelProperty(value = "菜品描述")
    private String description;
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
    private List<String> materials;
    @ColumnWidth(50)
    @ExcelProperty(value = "制作步骤")
    private int steps;

    //  创建 CookBook 对象时，自动生成一个 3 位数的 cookID 且首位不能为 0
    public CookBook() {
        this.cookID = (new Random().nextInt(999 - 100 + 1) + 100) * 1000 + UniqueIDGenerator.generateUniqueNumber();
    }


}
