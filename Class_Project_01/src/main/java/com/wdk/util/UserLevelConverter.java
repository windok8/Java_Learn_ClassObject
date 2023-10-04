package com.wdk.util;

import com.alibaba.excel.converters.Converter;
import com.alibaba.excel.converters.ReadConverterContext;

import com.alibaba.excel.converters.WriteConverterContext;
import com.alibaba.excel.metadata.GlobalConfiguration;
import com.alibaba.excel.metadata.data.ReadCellData;
import com.alibaba.excel.metadata.data.WriteCellData;
import com.alibaba.excel.metadata.property.ExcelContentProperty;
import com.sun.media.sound.SoftTuning;
import com.wdk.pojo.UserLevel;

/**
 * @author : Windok
 * @date: 2023-10-03
 * @Description:
 * @version: 1.0
 */
public class UserLevelConverter implements Converter<UserLevel> {


    /**
     * @Author: Windok
     * @Description:    将字符串转换为枚举类型
     * @param context  读取上下文
     **/
    @Override
    public UserLevel convertToJavaData(ReadConverterContext<?> context) throws Exception {
        System.out.println(context.getReadCellData().getStringValue());
        if(context.getReadCellData() == null)
            return null;
        if(context.getReadCellData().getStringValue().equals("超级管理员"))
            return UserLevel.SUPER_ADMIN;
        if(context.getReadCellData().getStringValue().equals("管理员"))
            return UserLevel.ADMIN;
        if(context.getReadCellData().getStringValue().equals("作家"))
            return UserLevel.AUTHOR;
        if(context.getReadCellData().getStringValue().equals("普通用户"))
            return UserLevel.USER;
        if(context.getReadCellData().getStringValue().equals("游客"))
            return UserLevel.GUEST;
        return null;
    }

    /**
     * @Author: Windok
     * @Description:
     * @param context
     * @return WriteCellData<?>
     **/
    @Override
    public WriteCellData<?> convertToExcelData(WriteConverterContext<UserLevel> context) throws Exception {
        return new WriteCellData<>(context.getValue().getDescription());
    }
}
