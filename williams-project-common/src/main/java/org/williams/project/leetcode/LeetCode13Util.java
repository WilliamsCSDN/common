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
 * 443. 压缩字符串
 *
 * @author Williams
 * @since 2025-08-04
 */

public class LeetCode13Util {

    public static void main(String[] args) {
        System.out.println(compress1("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaabbccc".toCharArray()));
    }

    // 给你一个字符数组 chars ，请使用下述算法压缩：
    //
    //从一个空字符串 s 开始。对于 chars 中的每组 连续重复字符 ：
    //
    //如果这一组长度为 1 ，则将字符追加到 s 中。
    //否则，需要向 s 追加字符，后跟这一组的长度。
    //压缩后得到的字符串 s 不应该直接返回 ，需要转储到字符数组 chars 中。需要注意的是，如果组长度为 10 或 10 以上，则在 chars 数组中会被拆分为多个字符。
    //
    //请在 修改完输入数组后 ，返回该数组的新长度。
    //
    //你必须设计并实现一个只使用常量额外空间的算法来解决此问题。
    public static char[] compress(char[] chars) {
        char b = 0;
        int c = 1;
        int t = 0;
        StringBuilder sb = new StringBuilder();
        for (char a : chars){
            if (t++ == 0){
                sb.append(a);
                b = a;
            }else {
                if (a == b){
                    c++;
                }else {
                    if (c != 1){
                        sb.append(c);
                    }
                    b = a;
                    sb.append(a);
                    c = 1;
                }
                if (t == chars.length){
                    sb.append(c);
                }
            }
        }
        return sb.toString().toCharArray();
    }

    public static int compress1(char[] chars) {
        int n = chars.length;
        int write = 0, left = 0;
        for (int read = 0; read < n; read++) {
            if (read == n - 1 || chars[read] != chars[read + 1]) {
                chars[write++] = chars[read];
                int num = read - left + 1;
                if (num > 1) {
                    int anchor = write;
                    while (num > 0) {
                        chars[write++] = (char) (num % 10 + '0');
                        num /= 10;
                    }
                    reverse(chars, anchor, write - 1);
                }
                left = read + 1;
            }
        }
        return write;
    }

    public static void reverse(char[] chars, int left, int right) {
        while (left < right) {
            char temp = chars[left];
            chars[left] = chars[right];
            chars[right] = temp;
            left++;
            right--;
        }
    }

}
