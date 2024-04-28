package org.williams.project.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 拥有最多糖果的孩子
 */
public class LeetCode3Util {
    public static void main(String[] args) {
        System.out.println(kidsWithCandies(new int[]{2,3,5,1,3},
                3));
    }

    public static List<Boolean> kidsWithCandies(int[] candies, int extraCandies) {
        int mid = Arrays.stream(candies).max().getAsInt() - extraCandies;
        List<Boolean> re = new ArrayList<>();
        for (int i : candies){
            re.add(i >= mid);
        }

        return re;
    }




}
