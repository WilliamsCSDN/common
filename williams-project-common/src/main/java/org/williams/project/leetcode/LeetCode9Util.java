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
 * 62. 不同路径
 *
 * @author Williams
 * @since 2025-04-03
 */

public class LeetCode9Util {

    public static int totalRoute(int x, int y) {
        if (x == 0 || y == 0) {
            return 1;
        }
        int[][] f = new int[x][y];
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                if (i == 0 || j == 0) {
                    f[i][j] = 1;
                } else {
                    f[i][j] = f[i - 1][j] + f[i][j - 1];
                }
            }
        }
        return f[x - 1][y - 1];
    }

    public static void main(String[] args) {

        System.out.println(totalRoute(3, 7));

    }


}
