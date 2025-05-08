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

import java.util.Arrays;

/**
 * 63. 不同路径 II
 *
 * @author Williams
 * @since 2025-04-07
 */

public class LeetCode10Util {

    public static void main(String[] args) {
        int [][] a = new int[][]{{0,1,0,0,0},{1,0,0,0,0},{0,0,0,0,0},{0,0,0,0,0}};
        System.out.println(uniquePathsWithObstacles1(a));
    }

    public static int uniquePathsWithObstacles(int[][] obstacleGrid) {
        if (obstacleGrid[0][0] == 1 || obstacleGrid[obstacleGrid.length -1][obstacleGrid[0].length - 1] == 1){
            return 0;
        }
        if ((obstacleGrid.length == 1 && Arrays.stream(obstacleGrid[0]).anyMatch(i -> i == 1)) ||
                obstacleGrid[0].length == 1 && Arrays.stream(obstacleGrid).anyMatch(i -> i[0] == 1)){
            return 0;
        }
        int [][]f = new int[obstacleGrid.length][obstacleGrid[0].length];
        for (int i = 0; i < obstacleGrid.length; i++){
            for (int j = 0; j < obstacleGrid[0].length; j++){
                if (obstacleGrid[i][j] == 1){
                    f[i][j] = 0;
                }else  if ((i == 0 || j == 0)){
                    if ((i > 0 && f[i - 1][j] == 0) || (j > 0 && f[i][j - 1] == 0)){
                        f[i][j] = 0;
                    }else {
                        f[i][j] = 1;
                    }
                }else {
                    if (obstacleGrid[i-1][j] == 1){
                        if (obstacleGrid[i][j - 1] == 1){
                            f[i][j] = 0;
                        }else {
                            f[i][j] = f[i][j-1];
                        }
                    }else if (obstacleGrid[i][j - 1] == 1){
                        if (obstacleGrid[i - 1][j] == 1){
                            f[i][j] = 0;
                        }else {
                            f[i][j] = f[i-1][j];
                        }
                    }else {
                        f[i][j] = f[i-1][j] + f[i][j-1];
                    }
                }
            }
        }

        return f[obstacleGrid.length - 1][obstacleGrid[0].length - 1];
    }

    public static int uniquePathsWithObstacles1(int[][] obstacleGrid) {
        if (obstacleGrid.length == 1 && obstacleGrid[0].length == 1 && obstacleGrid[0][0] == 1){
            return 0;
        }
        if (obstacleGrid[0][0] == 1){
            obstacleGrid[0][0] = 0;
        }else {
            obstacleGrid[0][0] = 1;
        }
        for (int i = 0; i < obstacleGrid.length; i++){
            for (int y = 0;y<obstacleGrid[0].length;y++){
                if (i == 0 && y == 0) continue;
                if (obstacleGrid[i][y] == 1){
                    obstacleGrid[i][y] = 0;
                }else {
                    if (i > 0 && y > 0){
                        obstacleGrid[i][y] = obstacleGrid[i - 1][y] + obstacleGrid[i][y - 1];
                    }else if (i > 0){
                        obstacleGrid[i][y] = obstacleGrid[i - 1][y];
                    }else {
                        obstacleGrid[i][y] = obstacleGrid[i][y-1];
                    }
                }
            }
        }
        return obstacleGrid[obstacleGrid.length - 1][obstacleGrid[0].length - 1];
    }
}
