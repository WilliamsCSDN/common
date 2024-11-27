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
package org.williams.project.component.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.williams.project.component.util.converters.*;

import java.util.Date;

/**
 * 类描述
 *
 * @author Williams
 * @since 2024-11-27
 */

@Component
public class AppUtil implements ApplicationContextAware {
    static ApplicationContext applicationContext;

    public static <T> T bean(Class<T> type) {
        return applicationContext.getBean(type);
    }

    public static Integer strToInt(String value, Integer def) {
        return new StringToInteger().convertFromAToB(value, def);
    }

    public static String intToStr(Integer value, String def) {
        return new IntegerToString().convertFromAToB(value, def);
    }

    public static <T> T bean(String type) {
        return (T) applicationContext.getBean(type);
    }

    @Override
    public void setApplicationContext(ApplicationContext ctx) throws BeansException {
        applicationContext = ctx;
    }

    ////////////////////////////////////////////////////////////////////////////////
    ////

    public static String propStr(String name, String defaultValue){
        return applicationContext.getEnvironment().getProperty(name, defaultValue);
    }

    public static String propStr(String name){
        return applicationContext.getEnvironment().getProperty(name);
    }

    public static Integer propInt(String name, Integer def){
        String val = propStr(name, null);
        if(val==null) return def;
        return new StringToInteger().convertFromAToB(val, def);
    }

    public static Long propLong(String name, Long def){
        String val = propStr(name, null);
        if(val==null) return def;
        return new StringToLong().convertFromAToB(val, def);
    }

    public static Float propFloat(String name, Float def){
        String val = propStr(name, null);
        if(val==null) return def;
        return new StringToFloat().convertFromAToB(val, def);
    }

    public static Short propShort(String name, Short def){
        String val = propStr(name, null);
        if(val==null) return def;
        return new StringToShort().convertFromAToB(val, def);
    }

    public static Double propDouble(String name, Double def){
        String val = propStr(name, null);
        if(val==null) return def;
        return new StringToDouble().convertFromAToB(val, def);
    }

    public static Boolean propBool(String name, Boolean def){
        String val = propStr(name, null);
        if(val==null) return def;
        return new StringToBoolean().convertFromAToB(val, def);
    }

    public static Date propDate(String name, Date def){
        String val = propStr(name, null);
        if(val==null) return def;
        return new StringToDate().convertFromAToB(val, def);
    }

    public static Date propDate(String name, String fmt,  Date def){
        String val = propStr(name, null);
        if(val==null) return def;
        return new StringToDate().setPattern(fmt).convertFromAToB(val, def);
    }

}