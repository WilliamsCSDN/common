package org.williams.project.utils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 151. 反转字符串中的单词
 */
public class LeetCode6Util {


    public static void main(String[] args) {
        System.out.println(reverseWords2("  hello world  "));
    }

    public static String reverseWords(String s) {
        List<String> collect = Arrays.stream(s.trim().split(" ")).collect(Collectors.toList());
        StringBuilder sb = new StringBuilder();
        for (int i = collect.size() - 1; i >= 0; i--){
            if (!("").equals(collect.get(i))){
                sb.append(collect.get(i));
                if (i != 0){
                    sb.append(" ");
                }
            }
        }

        return sb.toString();
    }

    public static String reverseWords2(String s) {
        s = s.trim();
        String sb = new String();
        String mid = new String();

        for (int i = s.length() - 1; i >= 0 ;i--){
            if (s.charAt(i) == ' '){
                if (mid.length() == 0){
                    continue;
                }else {
                    if (i == 0){
                        sb += mid;
                    }else {
                        sb += mid + " ";

                    }
                    mid = new String();
                }
            }else {
                mid = s.charAt(i) + mid;
            }
            if (i == 0 && mid.length() != 0){
                sb += mid;
            }
        }
        return sb;
    }




}
