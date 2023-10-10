package com.wdk.pojo;

import lombok.Data;

/**
 * @author : Windok
 * @date: 2023-10-03
 * @Description: 用户级别
 * @version: 1.0
 */

public enum UserLevel {

    SUPER_ADMIN("超级管理员"),
    ADMIN("管理员"),
    AUTHOR("作家"),
    USER("普通用户"),
    GUEST("游客");

    private final String description;

    UserLevel(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

}
