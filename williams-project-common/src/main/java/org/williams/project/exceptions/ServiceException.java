package org.williams.project.exceptions;

import lombok.Getter;

/**
 * @Author: Williams
 * @Description: 通用业务业务异常
 * @Date: 2018/7/2 16:54
 **/
@Getter
public class ServiceException extends RuntimeException {


    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(Throwable throwable) {
        super(throwable);
    }


}
