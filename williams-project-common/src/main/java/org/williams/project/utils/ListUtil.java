package org.williams.project.utils;


import cn.hutool.core.util.ObjectUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author Williams
 * @date 2022/8/22 11:00
 */
public class ListUtil {

    /**
     * 将集合按指定数量分组
     *
     * @param list     数据集合
     * @param quantity 分组数量
     * @return 分组结果
     */
    public static <T> List<List<T>> groupListByQuantity(List<T> list, int quantity) {

        if (list == null || list.size() == 0) {
            return null;
        }
        if (quantity <= 0) {
            new IllegalArgumentException("Wrong quantity.");
        }
        List<List<T>> wrapList = new ArrayList<List<T>>();
        int count = 0;
        while (count < list.size()) {
            wrapList.add(new ArrayList<T>(list.subList(count, (count + quantity) > list.size() ? list.size() : count + quantity)));
            count += quantity;
        }

        return wrapList;
    }

    /**
     * 利用list.contain() 去重
     *
     * @param list
     * @return
     */
    public static <T> List<T> removeDuplicateContain(List<T> list) {
        List<T> listTemp = new ArrayList<>();
        for (T aList : list) {
            if (!listTemp.contains(aList)) {
                listTemp.add(aList);
            }
        }
        return listTemp;
    }

    /**
     * 通过HashSet去重
     *
     * @param list 待去重的list
     * @return 去重后的list
     */
    public static <T> List<T> removeDuplicateHashSet(List<T> list) {
        HashSet<T> hs = new HashSet<>(list);
        list.clear();
        list.addAll(hs);
        return list;
    }

    /**
     * 数据分组
     *
     * @param l
     * @param threadNum 分组数量
     * @param <T>
     * @return
     */
    public static <T> List<List<T>> splitList(List<T> l, int threadNum) {
        List<List<T>> re = new ArrayList<>(threadNum);
        if (ObjectUtil.isEmpty(l) || threadNum <= 0) {
            return re;
        }
        int total = l.size();
        if (threadNum > total) {
            re.add(l);
            return re;
        }
        int splitNum = total / threadNum;
        // 余数大于0，每个分组补1
        int y = total % threadNum;
        if (y > 0) {
            splitNum++;
        }
        int last = 0;
        for (int i = 0; i < threadNum; i++) {
            if (i == threadNum - 1) {
                re.add(l.subList(last, total));
            } else if (y > i || y == 0) {
                re.add(l.subList(last, last + splitNum));
                last = last + splitNum;
            } else { // 有余数且在 线程数 > 余数
                re.add(l.subList(last, last + (splitNum - 1)));
                last = last + (splitNum - 1);
            }
        }
        return re;
    }

    public static <T, R> List<R> page(List<T> list, int pageNo, int pageSize, Function<T, R> function) {
        if (list == null || list.isEmpty()) {
            return Collections.emptyList();
        }
        if (pageNo <= 0) {
            pageNo = 1;
        }
        if (pageSize <= 0) {
            pageSize = 5;
        }
        return list.stream().skip((long) (pageNo - 1) * pageSize).limit(pageSize).map(function).collect(Collectors.toList());
    }

    public static <T> List<T> page(List<T> list, int pageNo, int pageSize) {
        if (list == null || list.isEmpty()) {
            return Collections.emptyList();
        }
        if (pageNo <= 0) {
            pageNo = 1;
        }
        if (pageSize <= 0) {
            pageSize = 5;
        }
        return list.stream().skip((long) (pageNo - 1) * pageSize).limit(pageSize).collect(Collectors.toList());
    }
}
