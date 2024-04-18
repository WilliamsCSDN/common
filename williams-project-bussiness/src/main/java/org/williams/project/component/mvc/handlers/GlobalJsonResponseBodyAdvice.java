package org.williams.project.component.mvc.handlers;


import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.apache.skywalking.apm.toolkit.trace.TraceContext;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.GsonHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;
import org.williams.project.component.mvc.result.HttpResponse;
import org.williams.project.component.mvc.result.HttpResult;

/**
 * @Author: LiuJieBang
 * @Description: 全局返回处理
 * @Date: 2022/4/17 16:51
 **/
@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalJsonResponseBodyAdvice implements ResponseBodyAdvice<Object> {

    private final ObjectMapper objectMapper;

    @Override
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {
        return aClass.isAssignableFrom(FastJsonHttpMessageConverter.class)
                || aClass.isAssignableFrom(MappingJackson2HttpMessageConverter.class)
                || aClass.isAssignableFrom(GsonHttpMessageConverter.class)
                || aClass.isAssignableFrom(StringHttpMessageConverter.class);

    }

    @SneakyThrows
    @Override
    public Object beforeBodyWrite(Object o, MethodParameter methodParameter, MediaType mediaType, Class aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        String traceId = TraceContext.traceId();
        if (o instanceof HttpResult){
            return o;
        }
        if (o instanceof HttpResponse) {
            HttpResponse<?> httpResponse = (HttpResponse<?>) o;
            httpResponse.setRequestId(traceId);
            return HttpResult.build(httpResponse);
        } else if (o instanceof String) {
            serverHttpResponse.getHeaders().setContentType(MediaType.APPLICATION_JSON);
            return objectMapper.writeValueAsString(HttpResult.build(HttpResponse.success(traceId, o)));
        } else {
            return HttpResult.build(HttpResponse.success(traceId, o));
        }
    }

}
