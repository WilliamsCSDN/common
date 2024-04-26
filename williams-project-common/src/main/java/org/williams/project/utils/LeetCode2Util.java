package org.williams.project.utils;

/**
 * 字符串的最大公因子
 */
public class LeetCode2Util {
    public static void main(String[] args) {
        System.out.println(convert1("AA",
                "A"));
    }

    public static String convert(String str1, String str2) {

        String maxStr = str1.length() > str2.length() ? str1 : str2;
        String minStr = str1.length() > str2.length() ? str2 : str1;

        int i = minStr.length();
        while (i > 0) {
            if (minStr.length() % i == 0 && maxStr.length() % i == 0) {
                String x = maxStr.substring(0, i);
                if (check(x, maxStr) && check(x, minStr)) {
                    return x;
                }
            }
            i--;
        }
        return "";
    }

    public static String convert1(String str1, String str2) {

        String maxStr = str1.length() > str2.length() ? str1 : str2;
        String minStr = str1.length() > str2.length() ? str2 : str1;

        int i = minStr.length();
        String x = maxStr.substring(0, gcd(maxStr.length(), minStr.length()));
        if (check(x, maxStr) && check(x, minStr)) {
            return x;
        }
        return "";
    }

    public static String convert2(String str1, String str2) {

        if (!str1.concat(str2).equals(str2.concat(str1))){
            return "";
        }
        return str1.substring(0, gcd(str1.length(), str2.length()));
    }



    private static boolean check(String x, String re) {
        StringBuilder mid = new StringBuilder(x);
        for (int i = 1; i < (re.length() / x.length()); i++) {
            mid.append(x);
        }
        return re.equals(mid.toString());
    }


    public static int gcd(int a, int b) {
        int remainder = a % b;
        while (remainder != 0) {
            a = b;
            b = remainder;
            remainder = a % b;
        }
        return b;
    }


}
