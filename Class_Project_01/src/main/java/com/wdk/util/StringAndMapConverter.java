package com.wdk.util;

import java.util.*;

/**
 * @author : Windok
 * @date: 2023-10-23
 * @Description:
 * @version: 1.0
 */
public class StringAndMapConverter {

    public static String convertMapToString(Map<String, List<String>> map) {
        StringBuilder sb = new StringBuilder();
        sb.append("{");

        for (Map.Entry<String, List<String>> entry : map.entrySet()) {
            sb.append(entry.getKey()).append("=[");
            for (String item : entry.getValue()) {
                sb.append(item).append(", ");
            }
            sb.delete(sb.length() - 2, sb.length()); // 移除最后一个逗号和空格
            sb.append("], ");
        }
        sb.delete(sb.length() - 2, sb.length()); // 移除最后一个逗号和空格
        sb.append("}");

        return sb.toString();
    }

    public static Map<String, List<String>> convertStringToMap(String inputString) {
        Map<String, List<String>> resultMap = new HashMap<>();
        inputString = inputString.substring(1, inputString.length() - 1); // 去掉大括号

        String[] entries = inputString.split("], ");
        for (String entry : entries) {
            String[] parts = entry.split("=");
            String key = parts[0];
            String valuesString = parts[1].substring(1); // 去掉左括号
            String[] values = valuesString.split(", ");
            resultMap.put(key, new ArrayList<>(Arrays.asList(values)));
        }

        return resultMap;
    }

    public static Map<Integer, String> parseRecipeDescription(String input) {
        Map<Integer, String> recipeDescription = new HashMap<>();

        // 去掉开头和结尾的花括号
        input = input.substring(1, input.length() - 1);

        // 以逗号和空格分割字符串
        String[] pairs = input.split(", ");

        // 以等号分割每个键值对字符串
        for (String pair : pairs) {
            String[] keyValue = pair.split("=");
            recipeDescription.put(Integer.parseInt(keyValue[0]), keyValue[1]);
        }

        return recipeDescription;
    }

}
