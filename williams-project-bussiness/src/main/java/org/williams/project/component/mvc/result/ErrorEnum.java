package org.williams.project.component.mvc.result;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Williams
 * @date 2023-08-10-16:26
 * @Description
 */
@Getter
@AllArgsConstructor
public enum ErrorEnum {

    INTERNAL_ERROR("InternalError","内部错误，未知错误"),

    // **** 身份认证与鉴权 ****
    AUTH_FAILURE("AuthFailure", "授权失败"),
    AUTH_FAILURE_SESSION("AuthFailure.Session","鉴权未通过, 缺少会话态或会话态失效"),
    AUTH_FAILURE_BUSINESS("AuthFailure.Business","认证与鉴权，业务登录态异常。"),
    // **** 验证码 ****
    CAPTCHA("CaptchaFailure","验证码错误"),
    CAPTCHA_NOT_MATCH("CaptchaFailure.NotMatch","验证码错误, 验证码不匹配"),
    CAPTCHA_REQUEST_LIMIT_EXCEEDED("CaptchaFailure.RequestLimitExceeded","验证码请求过于频繁"),

    // **** 客户端错误，请求错误****
    INVALID_REQUEST("InvalidRequest","请求错误"),
    INVALID_REQUEST_REPEATED("InvalidRequest.Repeated","请求被重放，触发防重放"),
    INVALID_REQUEST_LIMIT_EXCEEDED("InvalidRequest.LimitExceeded","请求限频"),
    INVALID_REQUEST_PARAM_NOT_READABLE("InvalidRequest.ParamNotReadable","请求参数无法读取"),
    INVALID_REQUEST_METHOD_NOT_SUPPORTED("InvalidRequest.MethodNotSupported","请求方式不支持"),
    INVALID_REQUEST_NOT_FOUND("InvalidRequest.NotFound","请求路径不存在"),
    // **** 客户端错误，请求错误，参数错误 ****
    INVALID_PARAMETER("InvalidParameter","缺少参数或参数不合法"),
    INVALID_PARAMETER_MISSING_PARAM("InvalidParameter.MissingParam","缺少参数"),
    INVALID_PARAMETER_INVALID_VALUE("InvalidParameter.InvalidValue","参数值不合法"),
    INVALID_PARAMETER_TYPE_MISMATCH("InvalidParameter.TypeMismatch","参数类型不匹配"),

    // **** 服务端错误，返回错误****
    INVALID_RESPONSE_MEDIA_TYPE_NOT_ACCEPTABLE("InvalidRequest.MediaTypeNotAcceptable","返回错误,媒体类型无法接受"),

    // **** 服务端错误 ****
    OPERATION("OperationFailure","操作失败, 原因未知"),
    OPERATION_BUSINESS("OperationFailure.Service","操作失败"),
    OPERATION_RESOURCE_NOT_FOUND("OperationFailure.ResourceNotFound","资源不存在"),
    OPERATION_USER_NOT_FOUND("OperationFailure.UserNotFound","用户不存在"),
    OPERATION_UNAUTHORIZED("OperationFailure.Unauthorized","未经授权的操作，操作越权"),
    ;



    private final String code;

    private final String message;
}
