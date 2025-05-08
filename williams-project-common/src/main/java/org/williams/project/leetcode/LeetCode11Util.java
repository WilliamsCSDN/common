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
 * 64. 最小路径和
 *
 * @author Williams
 * @since 2025-04-07
 */

public class LeetCode11Util {

    public static void main(String[] args) {
        int [][]a = new int[][]{{1,3,1},{1,5,1},{4,2,1}};
        System.out.println(minPathSum(a));
    }

    public static int minPathSum(int[][] grid) {
        int [][]f = new int[grid.length][grid[0].length];


        for (int i = 0; i < grid.length; i++){
            for (int j = 0; j < grid[0].length; j++){
                if (i == 0 && j == 0){
                    f[i][j] = grid[i][j] ;
                }else if (i == 0){
                    f[i][j] = grid[i][j] + f[i][j - 1];
                } else if (j ==0){
                    f[i][j] = grid[i][j] + f[i - 1][j];
                }else {
                    f[i][j] = Math.min(f[i-1][j], f[i][j-1]) + grid[i][j];
                }

            }
        }
        return f[grid.length -1][grid[0].length - 1];
    }

    public static int minPathSum1(int[][] grid) {
        for (int i = 0; i < grid.length; i++){
            for (int j = 0; j < grid[0].length; j++){
                if (i == 0 && j == 0){
                    continue;
                }else if (i == 0){
                    grid[i][j] = grid[i][j] + grid[i][j - 1];
                } else if (j ==0){
                    grid[i][j] = grid[i][j] + grid[i - 1][j];
                }else {
                    grid[i][j] = Math.min(grid[i-1][j], grid[i][j-1]) + grid[i][j];
                }

            }
        }
        return grid[grid.length -1][grid[0].length - 1];
    }
}
