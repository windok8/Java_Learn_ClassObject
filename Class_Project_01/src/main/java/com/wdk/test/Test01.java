package com.wdk.test;

import com.wdk.util.UniqueIDGenerator;

import java.util.Random;

/**
 * @author : Windok
 * @date: 2023-10-17
 * @Description:
 * @version: 1.0
 */
public class Test01 {

    public static void main(String[] args) {
        System.out.println((new Random().nextInt(999 - 100 + 1) + 100) * 1000 + UniqueIDGenerator.generateUniqueNumber());
        System.out.println((new Random().nextInt(999 - 100 + 1) + 100) * 1000 + UniqueIDGenerator.generateUniqueNumber());
    }
}
