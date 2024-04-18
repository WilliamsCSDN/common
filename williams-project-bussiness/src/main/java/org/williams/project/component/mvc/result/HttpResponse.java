package org.williams.project.component.mvc.result;

import lombok.Data;
import org.apache.skywalking.apm.toolkit.trace.TraceContext;

import java.io.Serializable;


/**
 * @author Williams
 * @date 2023-08-10-10:43
 * @Description
 */
@Data
public class HttpResponse<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    private HttpError error;

    private String requestId;

    private T body;


    private HttpResponse(){};


    public static <T> HttpResponse<T> fail(ErrorEnum errorEnum) {
        HttpResponse<T> response=new HttpResponse<>();
        response.setRequestId(TraceContext.traceId());
        response.setError(new HttpError(errorEnum.getCode(), errorEnum.getMessage()));
        return response;

    }

    public static <T> HttpResponse<T> fail(ErrorEnum errorEnum, String message) {
        HttpResponse<T> response=new HttpResponse<>();
        response.setRequestId(TraceContext.traceId());
        response.setError(new HttpError(errorEnum.getCode(), errorEnum.getMessage() + ":[" + message + "]"));
        return response;
    }

    public static <T> HttpResponse<T> fail(String code, String message) {
        HttpResponse<T> response=new HttpResponse<>();
        response.setRequestId(TraceContext.traceId());
        response.setError(new HttpError(code, message));
        return response;
    }

    public static <T> HttpResponse<T> success(String requestId, T body) {
        HttpResponse<T> response=new HttpResponse<>();
        response.setRequestId(requestId);
        response.setBody(body);
        return response;
    }
}
