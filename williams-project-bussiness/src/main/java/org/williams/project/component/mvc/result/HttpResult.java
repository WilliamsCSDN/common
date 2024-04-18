package org.williams.project.component.mvc.result;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author: Williams
 * @Description: restFul自定义响应类
 * @Date: 2018/7/2 16:54
 **/
@Data
public class HttpResult<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    private HttpResponse<T> response;


    private HttpResult(){};

    public static <T> HttpResult<T> build(HttpResponse<T> httpResponse) {
        HttpResult<T> httpResult = new HttpResult<>();
        httpResult.setResponse(httpResponse);
        return httpResult;
    }
}
