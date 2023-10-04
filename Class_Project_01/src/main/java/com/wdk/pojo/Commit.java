package com.wdk.pojo;

import com.alibaba.excel.annotation.ExcelProperty;
import com.wdk.util.UniqueIDGenerator;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.Random;

/**
 * @author : Windok
 * @date: 2023-10-03
 * @Description:
 * @version: 1.0
 */
@Data
public class Commit implements Serializable {

    @ExcelProperty(value = "评论ID")
    private Integer cid;
    @ExcelProperty(value = "评论内容")
    private String content;
    @ExcelProperty(value = "评论时间")
    private Date createTime;
    @ExcelProperty(value = "评论用户ID")
    private Integer uid;
    @ExcelProperty(value = "评论点赞数")
    private Integer likes;

    public Commit() {
        int randomNumber = (new Random().nextInt(999 - 100 + 1) + 100) * 1000 + UniqueIDGenerator.generateUniqueNumber();

    }


}
