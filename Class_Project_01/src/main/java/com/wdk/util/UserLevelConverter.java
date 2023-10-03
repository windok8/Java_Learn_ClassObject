package com.wdk.util;

import com.alibaba.excel.converters.Converter;
import com.alibaba.excel.converters.ReadConverterContext;

import com.alibaba.excel.converters.WriteConverterContext;
import com.alibaba.excel.metadata.data.WriteCellData;
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
        if(context.getReadCellData() == null)
            return null;
        if(context.getReadCellData().toString().equals("超级管理员"))
            return UserLevel.SUPER_ADMIN;
        if(context.getReadCellData().toString().equals("管理员"))
            return UserLevel.ADMIN;
        if(context.getReadCellData().toString().equals("作家"))
            return UserLevel.AUTHOR;
        if(context.getReadCellData().toString().equals("普通用户"))
            return UserLevel.USER;
        if(context.getReadCellData().toString().equals("游客"))
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
