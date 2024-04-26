package org.williams.project.utils;


/**
 * 交替合并字符串
 */
public class LeetCode1Util {
    public static void main(String[] args) {
        System.out.println(convert1("abc", "def"));
    }

    public static String convert(String word1, String word2) {
        StringBuilder sb = new StringBuilder();
        int minLen = word1.length() > word2.length() ? word2.length() : word1.length();
        for (int i = 0; i < minLen; i++){
            sb.append(word1.charAt(i));
            sb.append(word2.charAt(i));
        }
        if (word1.length() > word2.length()){
            sb.append(word1.substring(minLen));
        }else if (word1.length() < word2.length()){
            sb.append(word2.substring(minLen));
        }
        return sb.toString();
    }

    public static String convert1(String word1, String word2) {
        StringBuilder sb = new StringBuilder();

        int l1 = word1.length();
        int l2 = word2.length();
        int i1 = 0;
        int i2 = 0;


        while (i1 < l1 || i2 < l2){
            if(i1 < l1){
                sb.append(word1.charAt(i1));
                i1++;
            }

            if (i2 < l2){
                sb.append(word2.charAt(i2));
                i2++;
            }
        }

        return sb.toString();
    }

}
