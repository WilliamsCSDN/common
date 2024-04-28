package org.williams.project.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 反转字符串中的元音字母
 */
public class LeetCode5Util {

    private final static Map<Character, Object> existV = new HashMap<Character, Object>(){
        {
            put('a', 1);
            put('i', 1);
            put('e', 1);
            put('u', 1);
            put('o', 1);
            put('A', 1);
            put('E', 1);
            put('I', 1);
            put('O', 1);
            put('U', 1);
        }
    };
    public static void main(String[] args) {
        System.out.println(reverseVowels("leetcode"));
    }

    public static String reverseVowels(String s) {
        List<Character> in = new ArrayList<>();
        int slength = s.length();
        char[] charArray = s.toCharArray();
        for (char aa : charArray){
            if (existV.get(aa) != null) {
                in.add(aa);
            }
        }
        int length = in.size();
        if (length == 1) {
            return s;
        }


        int start = 0;
        for (int i = 0; i < length; i++){
            for(int j = start; j < slength;j++){
                if (s.charAt(j) == in.get(i)){
                    charArray[j] = in.get(length -1 -i);
                    start = j + 1;
                    break;
                }
            }
        }
        return new String(charArray);
    }




}
