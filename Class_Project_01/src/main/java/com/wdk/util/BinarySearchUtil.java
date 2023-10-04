package com.wdk.util;

/**
 * @author : Windok
 * @date: 2023-10-04
 * @Description:
 * @version: 1.0
 */
public class BinarySearchUtil {

    private BinarySearchUtil() {}
    public static int binarySearch(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (arr[mid] == target) {
                return mid; // 找到目标值，返回索引
            } else if (arr[mid] < target) {
                left = mid + 1; // 目标值在右半边
            } else {
                right = mid - 1; // 目标值在左半边
            }
        }

        return -1; // 目标值不存在于数组中
    }

}
