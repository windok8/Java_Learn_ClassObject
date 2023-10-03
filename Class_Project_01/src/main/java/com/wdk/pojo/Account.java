package com.wdk.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author : Windok
 * @date: 2023-10-03
 * @Description:
 * @version: 1.0
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Account implements Serializable {

    private Integer id;
    private String username;
    private String  password;

    //  创建 Account 对象时，随机生成 id 值
    public Account(String username, String password) {
        this.id = (int) (Math.random() * 100000);
        this.username = username;
        this.password = password;
    }


}
