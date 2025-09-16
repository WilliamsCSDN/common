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
 * 11. 盛最多水的容器
 *
 * @author Williams
 * @since 2025-08-04
 */

public class LeetCode16Util {

    public static void main(String[] args) {
        System.out.println(maxArea(new int[]{1,8,6,2,5,4,8,3,7}));
    }
    // 给定一个长度为 n 的整数数组 height 。有 n 条垂线，第 i 条线的两个端点是 (i, 0) 和 (i, height[i]) 。
    //
    //找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
    //
    //返回容器可以储存的最大水量。
    //
    //说明：你不能倾斜容器。

    public static int maxArea(int[] height) {
        int l = 0; int r = height.length - 1;
        int maxSize = 0;
        while(r > l) {
            int i = Math.min(height[l], height[r]) * (r - l);
            if (i > maxSize) {
                maxSize = i;
            }
            if (height[l] > height[r]){
                r--;
            }else {
                l++;
            }
        }
        return maxSize;
    }


}
