package com.wdk.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author : Windok
 * @date: 2023-10-04
 * @Description:    生成唯一ID
 * @version: 1.0
 */
public class UniqueIDGenerator {

    public static int generateUniqueNumber() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMd");
        String currentDate = dateFormat.format(new Date());

        // 将年月日字符串转换为整数
        int dateAsInt = Integer.parseInt(currentDate);

        // 应用一些算法来生成一个3位数的唯一正整数
        int uniqueNumber = (dateAsInt % 900) + 100;

        return uniqueNumber;
    }

}
