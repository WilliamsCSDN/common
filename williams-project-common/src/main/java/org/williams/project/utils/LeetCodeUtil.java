package org.williams.project.utils;

import java.util.ArrayList;
import java.util.List;

public class LeetCodeUtil {
    public static void main(String[] args) {
        System.out.println(convert1("PAYPALISHIRING", 3));
    }

    /**
     * z字形变换
     * @param s
     * @param numRows
     * @return
     */
    public static String convert1(String s, int numRows) {
        if (numRows == 1){
            return s;
        }

        List<StringBuilder> l = new ArrayList<>(numRows);
        for (int i = 0 ; i < numRows ; i++){
            l.add(new StringBuilder());
        }
        int f = -1;
        int b = 0;

        for (char a : s.toCharArray()){
            if (b == 0 || b == numRows - 1){
                f = -f;
            }
            l.get(b).append(a);
            b += f;

        }
        StringBuilder re = new StringBuilder();
        for (StringBuilder sb : l){
            re.append(sb);
        }


        return re.toString();
    }

    public static String convert(String s, int numRows) {

        if (numRows == 1){
            return s;
        }
        String [][]a = new String[numRows][s.length()];

        if(numRows == 2){
            StringBuilder sb1 = new StringBuilder();
            StringBuilder sb2 = new StringBuilder();
            for(int i = 0; i < s.length(); i++){
                if (i % 2 == 0){
                    sb1.append(s.charAt(i));
                }else {
                    sb2.append(s.charAt(i));
                }
            }
            return sb1.append(sb2).toString();
        }

        // 确定的数量
        int num = numRows - 2;

        // 上一次最后的值
        int total = numRows;

        int l = s.length();
        char[] c = s.toCharArray();
        int ii = 0;
        int jj = 0;
        for(int i = 0; i < l; i++){
            if (i < total){
                a[ii][jj] = String.valueOf(c[i]);
                if (i != total - 1){
                    ii++;
                }
            }else{
                ii--;
                jj++;
                a[ii][jj] = String.valueOf(c[i]);
                if (--num == 0){
                    ii--;
                    jj++;
                    num = numRows - 2;
                    total += num + numRows;
                }
            }
        }

        StringBuilder sb = new StringBuilder();

        for (int i = 0 ; i < numRows; i++){
            for (int j = 0; j< s.length();j++){
                if (a[i][j] != null){
                    sb.append(a[i][j]);
                }
            }
        }

        return sb.toString();
    }
}
