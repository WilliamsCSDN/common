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
package org.williams.project.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * 120. 三角形最小路径和
 *
 * @author Williams
 * @since 2025-04-07
 */

public class LeetCode12Util {

    public static void main(String[] args) {
        List<List<Integer>> a  = new ArrayList<>();
        List<Integer> b1 = new ArrayList<>();
        b1.add(2);

        List<Integer> b2 = new ArrayList<>();
        b2.add(3);
        b2.add(4);

        List<Integer> b3 = new ArrayList<>();
        b3.add(6);
        b3.add(5);
        b3.add(7);
        List<Integer> b4 = new ArrayList<>();
        b4.add(4);
        b4.add(1);
        b4.add(8);
        b4.add(3);

        a.add(b1);
        a.add(b2);
        a.add(b3);
        a.add(b4);
        System.out.println(minimumTotal(a));
    }

    public static int minimumTotal(List<List<Integer>> triangle) {
        if (triangle.size() == 1){
            return triangle.get(0).stream().min(Integer::compareTo).get();
        }else {
            for (int i = 1; i < triangle.size(); i++){
                for (int j = 0; j < triangle.get(i).size(); j++){

                    if (j == 0){
                        triangle.get(i).set(j, triangle.get(i - 1).get(0)  + triangle.get(i).get(j));
                    }else if (j == triangle.get(i).size() - 1){
                        triangle.get(i).set(j, triangle.get(i - 1).get(triangle.get(i - 1).size() - 1)  + triangle.get(i).get(j));
                    }else {
                        triangle.get(i).set(j, Math.min(triangle.get(i - 1).get(j - 1) , triangle.get(i - 1).get(j)) + triangle.get(i).get(j));
                    }
                }
            }
            return triangle.get(triangle.size() - 1).stream().min(Integer::compareTo).get();
        }
    }

}
