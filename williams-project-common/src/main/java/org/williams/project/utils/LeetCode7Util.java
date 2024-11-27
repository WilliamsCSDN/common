package org.williams.project.utils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 151. 除自身以外数组的乘积
 */
public class LeetCode7Util {


    public static void main(String[] args) {
        System.out.println(productExceptSelf(new int[]{1,2,3,4}));
    }

    public static int[] productExceptSelf(int[] nums) {
        Map<Integer, Integer> a = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (a.get(nums[i]) == null) {
                int mid = 1;
                for (int j = 0; j < nums.length; j++) {
                    if (i!=j){
                        mid *= nums[j];
                    }
                }
                a.put(nums[i], mid);
            }
        }
        for (int i = 0; i < nums.length; i++) {
            nums[i] = a.get(nums[i]);
        }
        return nums;
    }

//    public static int[] productExceptSelf1(int[] nums) {
//
//        int[] l = new int[nums.length];
//        int[] r = new int[nums.length];
//
//        l[0] = 1;
//        for (int i = 1; i < nums.length; i++) {
//            l[i] = l[i - 1] * nums[i-1];
//        }
//
//        r[0] = 1;
//        for (int i = nums.length; i > 1; i--) {
//            r[i] = r[i - 1] * nums[i-1];
//        }
//
//
//
//
//    }




}
