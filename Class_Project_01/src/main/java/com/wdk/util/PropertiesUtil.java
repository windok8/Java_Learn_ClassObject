package com.wdk.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author : Windok
 * @date: 2023-10-15
 * @Description:    读取配置文件
 * @version: 1.0
 */
public class PropertiesUtil {

    private Properties properties;
    public PropertiesUtil(String path){
        properties=new Properties();
        InputStream inputStream = this.getClass().getResourceAsStream(path);
        try {
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public String getProperties(String key){
        return properties.getProperty(key);
    }

}
