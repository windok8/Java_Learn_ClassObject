package com.wdk.pojo;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.DateTimeFormat;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.ContentStyle;
import lombok.Data;

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
public class CookBook {

    @ExcelProperty(value = "菜品ID")
    private Integer cookID;
    @ExcelProperty(value = "菜品名称")
    private String title;
    @ExcelProperty(value = "菜谱作者")
    private String author;
    @ExcelProperty(value = "菜品描述")
    private String description;
    @ColumnWidth(23)
    @ExcelProperty(value = "创作时间")
    @DateTimeFormat(value = "yyyy年MM月dd HH:mm:ss")
    private String createTime;
    @ColumnWidth(23)
    @ExcelProperty(value = "更新时间")
    @DateTimeFormat(value = "yyyy年MM月dd HH:mm:ss")
    private String updateTime;
    @ColumnWidth(50)
    @ExcelProperty(value = "准备材料")
    private List<String> materials;
    @ColumnWidth(50)
    @ExcelProperty(value = "制作步骤")
    private List<String> steps;

    //  创建 CookBook 对象时，自动生成一个 3 位数的 cookID 且首位不能为 0
    public CookBook() {
        this.cookID =new Random().nextInt(999 - 100 + 1) + 100;
    }


}
