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

import java.util.HashMap;
import java.util.Map;

/**
 * 643. 子数组最大平均数 I
 *
 * @author Williams
 * @since 2025-08-04
 */

public class LeetCode17Util {

    public static void main(String[] args) {
        System.out.println(findMaxAverage(new int[]{1,0,1,4,2}, 4));
    }
    // 给你一个整数数组 nums 和一个整数 k 。
    //
    //每一步操作中，你需要从数组中选出和为 k 的两个整数，并将它们移出数组。
    //
    //返回你可以对数组执行的最大操作数。

    public static double findMaxAverage(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i : nums) {
            map.computeIfAbsent(i, a -> 0);
            map.put(i, map.get(i) + 1);
        }

        Integer kk = null;

        for (Map.Entry<Integer, Integer> e : map.entrySet()) {
            if (map.get(k - e.getKey()) != null) {
                kk = e.getKey();
            }
        }
        if (kk != null) {
            if (map.get(kk) > 1) {
                map.put(kk, map.get(kk) - 1);
            } else {
                map.remove(kk);
            }
            if (map.get(k - kk) > 1) {
                map.put(k - kk, map.get(k - kk) - 1);
            } else {
                map.remove(k - kk);
            }
            map.remove(0);
        }
        int total = 0;
        int s = 0;
        for (Map.Entry<Integer, Integer> e : map.entrySet()) {
            total += (e.getKey() * e.getValue());
            s += e.getValue();
        }



        return (double) total / s;
    }
    public double findMaxAverage1(int[] nums, int k) {
        int sum = 0;
        int n = nums.length;
        for (int i = 0; i < k; i++) {
            sum += nums[i];
        }
        int maxSum = sum;
        for (int i = k; i < n; i++) {
            sum = sum - nums[i - k] + nums[i];
            maxSum = Math.max(maxSum, sum);
        }
        return 1.0 * maxSum / k;
    }



}
