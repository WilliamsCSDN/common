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

import org.springframework.util.StringUtils;

/**
 * 392. 判断子序列
 *
 * @author Williams
 * @since 2025-08-04
 */

public class LeetCode15Util {

    public static void main(String[] args) {
        System.out.println(isSubsequence("b","abc"));
    }
    // 给定字符串 s 和 t ，判断 s 是否为 t 的子序列。
    //
    //字符串的一个子序列是原始字符串删除一些（也可以不删除）字符而不改变剩余字符相对位置形成的新字符串。（例如，"ace"是"abcde"的一个子序列，而"aec"不是）。
    //
    //进阶：
    //
    //如果有大量输入的 S，称作 S1, S2, ... , Sk 其中 k >= 10亿，你需要依次检查它们是否为 T 的子序列。在这种情况下，你会怎样改变代码？
    //

    public static boolean isSubsequence(String s, String t) {
        if("".equals(s)){
            return true;
        }
        if("".equals(t)){
            return false;
        }
        char[] ss = s.toCharArray();
        char[] tt = t.toCharArray();

        int ii =0;

        for (int i = 0;i<tt.length;i++){
            if (ss.length > ii && ss[ii] == tt[i]){
                ii++;
            }
        }
        return ii > s.length()-1;
    }

    public static boolean isSubsequence1(String s, String t) {
        int ii =0;
        for (int i = 0;i<t.length();i++){
            if (s.length() > ii && s.charAt(ii) == t.charAt(i)){
                ii++;
            }
        }
        return ii == s.length();
    }


}
