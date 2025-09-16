/*
 * Copyright (C) 2011-present ShenZhen iBOXCHAIN Information Technology Co.,Ltd.
 *
 * All right reserved.
 *
 * This software is the confidential and proprietary
 * information of iBOXCHAIN Company of China.
 * ("Confidential Information"). You shall not disclose
 * such Confidential Information and shall use it only
 * in accordance with the terms of the contract agreement
 * you entered into with iBOXCHAIN inc.
 */
package org.williams.project.leetcode;

/**
 * 443. 压缩字符串
 *
 * @author Williams
 * @since 2025-08-04
 */

public class LeetCode14Util {

    public static void main(String[] args) {
        int[] a =new int[]{1,2,0,1,0,3,12};
        moveZeroes1(a);
        for (int i : a) {
            System.out.println(i);
        }
    }

    public static void moveZeroes(int[] nums) {

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0){
                continue;
            }
            for (int ii = i + 1; ii < nums.length; ii++) {
                if (nums[ii] !=0){
                    nums[i] = nums[ii];
                    nums[ii] = 0;
                    break;
                }
            }

        }

    }

    public static void moveZeroes1(int[] nums) {
        int n = nums.length, left = 0, right = 0;
        while (right < n) {
            if (nums[right] != 0) {
                swap(nums, left, right);
                left++;
            }
            right++;
        }
    }

    public static void swap(int[] nums, int left, int right) {
        int temp = nums[left];
        nums[left] = nums[right];
        nums[right] = temp;
    }

}
