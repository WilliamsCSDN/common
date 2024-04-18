package org.williams.project.component.mvc.handlers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.williams.project.component.mvc.result.ErrorEnum;
import org.williams.project.component.mvc.result.HttpResponse;
import org.williams.project.component.mvc.result.HttpResult;
import org.williams.project.exceptions.ServiceException;
import org.williams.project.exceptions.SystemException;

import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;


/**
 * @Author: LiuJieBang
 * @Description: 全局异常处理返回处理
 * @Date: 2018/7/2 16:51
 **/
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler<T> {


    /**
     * Get请求参数错误时的处理
     */
    @ExceptionHandler(NoHandlerFoundException.class)
    public HttpResult<T> noHandlerFoundExceptionHandler(NoHandlerFoundException e, HttpServletResponse resp){
        resp.setStatus(HttpStatus.NOT_FOUND.value());
        return HttpResult.build(HttpResponse.fail(ErrorEnum.INVALID_REQUEST_NOT_FOUND,e.getRequestURL()));
    }

    /**
     * 获取bindingResult中的错误信息
     *
     * @param e BindException
     * @return HttpResult
     */
    @ExceptionHandler(value = BindException.class)
    public HttpResult<T> bindExceptionHandler(BindException e) {
        return HttpResult.build(HttpResponse.fail(ErrorEnum.INVALID_PARAMETER));
    }

    /**
     * 请求参数类型不匹配
     *
     * @param e TypeMismatchException
     * @return HttpResult
     */
    @ExceptionHandler({TypeMismatchException.class})
    public HttpResult<T> typeMismatchExceptionHandler(TypeMismatchException e) {
        return HttpResult.build(HttpResponse.fail(ErrorEnum.INVALID_PARAMETER_TYPE_MISMATCH, "参数:" + e.getPropertyName() + "类型应该为:" + e.getRequiredType()));
    }

    /**
     * 请求参数个数不匹配
     *
     * @param e MissingServletRequestParameterException
     * @return HttpResult
     */
    @ExceptionHandler(value = MissingServletRequestParameterException.class)
    public HttpResult<T> missingServletRequestParameterExceptionHandler(MissingServletRequestParameterException e) {
        return HttpResult.build(HttpResponse.fail(ErrorEnum.INVALID_PARAMETER_MISSING_PARAM, "参数名称为:" + e.getParameterName()));
    }

    /**
     * 请求方式错误
     *
     * @param e HttpRequestMethodNotSupportedException
     * @return ResultView
     */
    @ExceptionHandler(value = HttpRequestMethodNotSupportedException.class)
    public HttpResult<T> httpRequestMethodNotSupportedExceptionHandler(HttpRequestMethodNotSupportedException e) {
        return HttpResult.build(HttpResponse.fail(ErrorEnum.INVALID_REQUEST_METHOD_NOT_SUPPORTED));
    }

    /**
     * Valid 参数校验异常(实体类字段校验)
     *
     * @param e MethodArgumentNotValidException
     * @return HttpResult
     */
    @ExceptionHandler({MethodArgumentNotValidException.class})
    public HttpResult<T> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {
        BindingResult bindingResult = e.getBindingResult();
        return HttpResult.build(HttpResponse.fail(ErrorEnum.INVALID_PARAMETER, Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage()));
    }

    /**
     * 入参解析错误
     *
     * @param e HttpMessageNotReadableException
     * @return HttpResult
     */
    @ExceptionHandler({HttpMessageNotReadableException.class})
    public HttpResult<T> HttpMessageNotReadableExceptionHandler(HttpMessageNotReadableException e) {
        return HttpResult.build(HttpResponse.fail(ErrorEnum.INVALID_REQUEST_PARAM_NOT_READABLE, e.getMessage()));
    }

    /**
     * Violation 参数校验异常
     *
     * @param e ConstraintViolationException
     * @return HttpResult
     */
    @ExceptionHandler(value = ConstraintViolationException.class)
    public HttpResult<T> constraintViolationExceptionHandler(ConstraintViolationException e) {
        Set<ConstraintViolation<?>> constraintViolations = e.getConstraintViolations();
        String message = constraintViolations.stream().map(ConstraintViolation::getMessageTemplate).collect(Collectors.joining(","));
        return HttpResult.build(HttpResponse.fail(ErrorEnum.INVALID_PARAMETER, message));
    }

    /**
     * 业务异常
     *
     * @param e ServiceException
     * @return HttpResult
     */
    @ExceptionHandler(value = ServiceException.class)
    public HttpResult<T> serviceExceptionHandler(ServiceException e) {
        return HttpResult.build(HttpResponse.fail(ErrorEnum.INVALID_REQUEST.getCode(), e.getMessage()));
    }

    /**
     * 系统异常
     *
     * @param e SystemException
     * @return HttpResult
     */
    @ExceptionHandler(value = SystemException.class)
    public HttpResult<T> systemExceptionHandler(SystemException e) {
        return HttpResult.build(HttpResponse.fail(ErrorEnum.INTERNAL_ERROR.getCode(), e.getMessage()));
    }

    /**
     * 运行时异常
     *
     * @param e RuntimeException
     * @return HttpResult
     */
    @ExceptionHandler(value = RuntimeException.class)
    public HttpResult<T> runtimeExceptionHandler(RuntimeException e) {
        return HttpResult.build(HttpResponse.fail(ErrorEnum.INTERNAL_ERROR));
    }

    /**
     * 全局异常
     *
     * @param e Exception
     * @return HttpResult
     */
    @ExceptionHandler(value = Exception.class)
    public HttpResult<T> exceptionHandler(Exception e) {
        return HttpResult.build(HttpResponse.fail(ErrorEnum.INTERNAL_ERROR));
    }

}
