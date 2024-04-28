package org.williams.project.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 种花问题
 */
public class LeetCode4Util {
    public static void main(String[] args) {
        System.out.println(canPlaceFlowers(new int[]{0,0,1,0,1},
                1));
    }

    public static boolean canPlaceFlowers(int[] flowerbed, int n) {
        int re = 0;
        if (flowerbed.length == 1 && flowerbed[0] == 0){
            re = 1;
        }else {
            for (int i = 0; i<flowerbed.length; i++){
                if (i == 0){
                    if (flowerbed[i] == 0 && flowerbed[i + 1] == 0){
                        flowerbed[i] = 1;
                        re++;
                    }
                } else if (i == flowerbed.length - 1){
                    if (flowerbed[i] == 0 && flowerbed[i-1] == 0 ){
                        flowerbed[i] = 1;
                        re++;
                    }
                } else {
                    if (flowerbed[i-1] == 0 && flowerbed[i] == 0 && flowerbed[i + 1] == 0){
                        flowerbed[i] = 1;
                        re++;
                    }
                }

            }
        }
        return re >= n;
    }




}
