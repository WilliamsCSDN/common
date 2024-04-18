package org.williams.project.utils;

import cn.hutool.core.bean.BeanUtil;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 转换
 * Created by jinjin on 2020-09-22.
 */
public class ConvertUtil {
    public static <T, S> T convert(final S s, Class<T> clz) {
        return s == null ? null : BeanUtil.copyProperties(s, clz);
    }

    public static <T, S> List<T> convertList(List<S> s, Class<T> clz) {
        return s == null ? null : s.stream().map(vs -> BeanUtil.copyProperties(vs, clz)).collect(Collectors.toList());
    }

    public static <T, S> Set<T> convertSet(Set<S> s, Class<T> clz) {
        return s == null ? null : s.stream().map(vs -> BeanUtil.copyProperties(vs, clz)).collect(Collectors.toSet());
    }


}
