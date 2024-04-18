package org.williams.project.exceptions;

import lombok.Getter;

/**
 * @author Williams
 * @date 2023-11-28-19:53
 * @Description
 */
@Getter
public class SystemException extends RuntimeException {

    public SystemException(String message) {
        super(message);
    }

    public SystemException(Throwable throwable) {
        super(throwable);
    }
}