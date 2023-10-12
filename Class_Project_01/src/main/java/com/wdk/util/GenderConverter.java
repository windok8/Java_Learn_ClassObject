package com.wdk.util;

import com.alibaba.excel.converters.Converter;
import com.alibaba.excel.converters.ReadConverterContext;
import com.alibaba.excel.converters.WriteConverterContext;
import com.alibaba.excel.metadata.data.WriteCellData;
import com.wdk.pojo.GenderEnum;

/**
 * @author : Windok
 * @date: 2023-10-03
 * @Description:
 * @version: 1.0
 */
public class GenderConverter implements Converter<GenderEnum> {

    /**
     * @Author: Windok
     * @Description:    将字符串转换为枚举类型
     * @param context  读取上下文
     **/
    @Override
    public GenderEnum convertToJavaData(ReadConverterContext<?> context) throws Exception {
        if(context.getReadCellData() == null)
            return null;
        if (context.getReadCellData().getStringValue().equals("男"))
            return GenderEnum.MAN;
        else if (context.getReadCellData().getStringValue().equals("女"))
            return GenderEnum.WOMAN;
        else if (context.getReadCellData().getStringValue().equals("未知"))
            return GenderEnum.UNKNOWN;
        return null;
    }

    /**
     * @Author: Windok
     * @Description:
     * @param context
     * @return WriteCellData<?>
     **/
    @Override
    public WriteCellData<?> convertToExcelData(WriteConverterContext<GenderEnum> context) throws Exception {
        return new WriteCellData<>(context.getValue().getDescription());
    }
}
