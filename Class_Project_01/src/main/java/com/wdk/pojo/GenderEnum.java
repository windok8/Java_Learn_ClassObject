package com.wdk.pojo;

/**
 * @author : Windok
 * @date: 2023-10-03
 * @Description:
 * @version: 1.0
 */
public enum GenderEnum {

    MAN("男"),
    WOMAN("女"),
    UNKNOWN("未知");

    private final String description;

    GenderEnum(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

}
