package com.wdk.pojo;

/**
 * @author : Windok
 * @date: 2023-10-17
 * @Description:
 * @version: 1.0
 */
public enum RecipeStatus {

    APPROVED("审核通过"),
    PENDING("审核中"),
    REJECTED("审核未通过");

    private String description;

    RecipeStatus(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

}
