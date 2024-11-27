package org.williams.project.utils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 334. 递增的三元子序列
 */
public class LeetCode8Util {

    public static void main(String[] args) {

    }

    public static boolean check(int[] nums){
        if (nums.length < 3){
            return false;
        }

        if (Arrays.stream(nums).distinct().count() < 3){
            return false;
        }

        int mid;
        int first;
        boolean f = false;
        for (int i = 0; i <= nums.length - 3; i++) {
            mid = nums[i];
            first = mid;
            int ii = 1;
            for (int j = i + 1; j < nums.length; j++) {

                if (nums[j] > mid) {
                    ii++;
                    mid = nums[j];
                    f = true;
                }else if (first < nums[j] && nums[j] < mid && f){
                    mid = nums[j];
                }
                if (ii == 3) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean check1(int[] nums){
        if (nums.length < 3){
            return false;
        }
        // left
        int[] left = new int[nums.length];
        int[] right = new int[nums.length];

        left[0] = nums[0];
        right[nums.length - 1] = nums[nums.length - 1];
        for (int i = 1; i <= nums.length - 2; i++) {
            left[i] = Math.min(nums[i], left[i - 1]);
        }

        for (int i = nums.length - 2; i >= 1; i--) {
            right[i] = Math.max(nums[i], right[i + 1]);
        }

        for (int i = 1; i <= nums.length - 2; i++) {
            if (left[i - 1] < nums[i] && nums[i] < right[i + 1]){
                return true;
            }
        }
        return false;
    }
}
