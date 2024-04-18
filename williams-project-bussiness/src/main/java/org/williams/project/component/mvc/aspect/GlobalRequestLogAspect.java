package org.williams.project.component.mvc.aspect;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @author LiuJieBang
 * @date 2023-05-30-20:40
 * @Description
 */
@Slf4j
@Aspect
@Component
public class GlobalRequestLogAspect {


    @Pointcut(value = "execution(public * org.williams.project.web..*.*(..))")
    public void requestLog() {
    }


    @Before("requestLog()")
    public void doBefore(JoinPoint joinPoint) {
        // 开始打印请求日志
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        // 打印请求 url
        log.info("Request URL            : {}", request.getRequestURL().toString());
        // 打印 Http method
        log.info("Request HTTP Method    : {}", request.getMethod());
        // 打印调用 controller 的全路径以及执行方法
        log.info("Request Class Method   : {}.{}", joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName());
        // 打印请求的 IP
        log.info("Request IP             : {}", request.getRemoteAddr());
        // 打印请求入参
        log.info("Request Args           : {}", JSON.toJSONString(joinPoint.getArgs()));
    }

}
