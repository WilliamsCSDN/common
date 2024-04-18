package org.williams.project.component.mvc.aspect;


import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * @Author: Williams
 * @Description: ExceptionHandler日志切面
 * @Date: 2018/6/27 15:03
 **/
@Aspect
@Component
@Slf4j
public class GlobalExceptionLogAspect {

    @Pointcut("@annotation(exceptionHandler)")
    public void exceptionLog(ExceptionHandler exceptionHandler) {

    }

    @Before("exceptionLog(exceptionHandler)")
    public void doBefore(JoinPoint joinPoint, ExceptionHandler exceptionHandler) {

        log.error("Request Error          : [" + getExceptionNames(exceptionHandler) + "]", (Throwable) joinPoint.getArgs()[0]);
    }


    @AfterReturning(pointcut = "exceptionLog(exceptionHandler)", returning = "object")
    public void doAfterReturning(Object object, ExceptionHandler exceptionHandler) {
        if (object != null) {
            log.info("Request Error Return   :[" + getExceptionNames(exceptionHandler) + "]{}", JSONObject.toJSONString(object));
        }
    }


    private String getExceptionNames(ExceptionHandler exceptionHandler) {
        Class<? extends Throwable>[] errors = exceptionHandler.value();
        return Arrays.stream(errors).map(Class::getSimpleName).collect(Collectors.joining(","));
    }
}
