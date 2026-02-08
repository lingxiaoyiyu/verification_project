package com.api.common.exception;

import cn.dev33.satoken.exception.NotLoginException;
import com.api.common.base.Result;
import com.api.common.utils.FunctionUtil;
import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.util.ArrayList;
import java.util.List;

/**
 * GlobalExceptionHandler 是一个全局异常处理器，使用 Spring 的 @RestControllerAdvice 注解，
 * 它会捕获并处理控制器层的所有异常，返回统一格式的 Result 对象。
 *
 * @author 裴金伟
 * @version 1.0
 * @since 2024-05-16
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);
    @Value("${config.result.code.error:500}")
    private int errorCode;
    @Value("${config.result.code.badRequest:400}")
    private int badRequestCode;
    @Value("${config.result.code.unauthorized:401}")
    private int unauthorized;
    @Value("${config.result.code.notFound:404}")
    private int notFoundCode;
    @Value("${config.result.code.methodNotAllowed:405}")
    private int methodNotAllowed;

    /**
     * 处理 ServerException 异常。
     * 返回状态码400和异常消息。
     *
     * @param e ServerException 异常对象
     * @return Result<String> 包含错误信息的 Result 对象
     */
    @ExceptionHandler(ServerException.class)
    public Result<List<Object>> serverExceptionHandling(ServerException e) {
        LOGGER.error("业务异常 ", e);
        return Result.fail(badRequestCode, e.getMessage());
    }

    /**
     * 处理 MethodArgumentNotValidException 异常，通常表示请求参数校验失败。
     * 返回状态码400和错误消息。
     *
     * @param e MethodArgumentNotValidException 异常对象
     * @return Result<String> 包含错误信息的 Result 对象
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result<List<Object>> validationExceptionHandling(MethodArgumentNotValidException e) {
        LOGGER.error("请求参数校验失败 ", e);
        ArrayList<String> messages = new ArrayList<>();
        // 提取 FieldError 信息并添加到错误信息中
        e.getBindingResult().getAllErrors().forEach(error -> {
            if (messages.isEmpty()) {
                if (error instanceof FieldError fieldError) {
                    messages.add(fieldError.getDefaultMessage());
                }
            }
        });
        return Result.fail(badRequestCode, String.join(",", messages));
    }

    /**
     * 处理 ServerException 异常。
     * 返回状态码400和异常消息。
     *
     * @param e ServerException 异常对象
     * @return Result<String> 包含错误信息的 Result 对象
     */
    @ExceptionHandler(NotLoginException.class)
    public Result<List<Object>> NotLoginExceptionHandling(NotLoginException e) {
        LOGGER.error("业务异常 ", e);
        String message;
        if(e.getType().equals(NotLoginException.NOT_TOKEN)) {
            message = NotLoginException.NOT_TOKEN_MESSAGE;
        }
        else if(e.getType().equals(NotLoginException.INVALID_TOKEN)) {
            message = NotLoginException.INVALID_TOKEN_MESSAGE;
        }
        else if(e.getType().equals(NotLoginException.TOKEN_TIMEOUT)) {
            message = NotLoginException.TOKEN_TIMEOUT_MESSAGE;
        }
        else if(e.getType().equals(NotLoginException.BE_REPLACED)) {
            message = NotLoginException.BE_REPLACED_MESSAGE;
        }
        else if(e.getType().equals(NotLoginException.KICK_OUT)) {
            message = NotLoginException.KICK_OUT_MESSAGE;
        }
        else if(e.getType().equals(NotLoginException.TOKEN_FREEZE)) {
            message = NotLoginException.TOKEN_FREEZE_MESSAGE;
        }
        else if(e.getType().equals(NotLoginException.NO_PREFIX)) {
            message = NotLoginException.NO_PREFIX_MESSAGE;
        }
        else {
            message = NotLoginException.DEFAULT_MESSAGE;
        }

        return Result.fail(unauthorized, message);
    }

    /**
     * 处理 HttpRequestMethodNotSupportedException 异常，通常表示访问的uri不存在
     * 返回状态码404和错误消息"权限不足"。
     *
     * @param e HttpRequestMethodNotSupportedException 异常对象
     * @return Result<String> 包含错误信息的 Result 对象
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public Result<List<Object>> requestMethodNotSupportedExceptionHandling(HttpRequestMethodNotSupportedException e) {
        LOGGER.error("请求方式错误 ", e);
        return Result.fail(methodNotAllowed, FunctionUtil.getI18nString("result.methodNotAllowed"));
    }

    /**
     * 处理 NoHandlerFoundException 异常，通常表示访问的uri不存在
     * 返回状态码404和错误消息"权限不足"。
     *
     * @param e NoHandlerFoundException 异常对象
     * @return Result<String> 包含错误信息的 Result 对象
     */
    @ExceptionHandler(NoHandlerFoundException.class)
    public Result<List<Object>> notFoundExceptionHandling(NoHandlerFoundException e) {
        LOGGER.error("找不到访问资源 ", e);
        return Result.fail(notFoundCode, FunctionUtil.getI18nString("result.notFound"));
    }

    /**
     * 处理 NoResourceFoundException 异常，通常表示访问的uri不存在
     * 返回状态码404和错误消息"权限不足"。
     *
     * @param e NoHandlerFoundException 异常对象
     * @return Result<String> 包含错误信息的 Result 对象
     */
    @ExceptionHandler(NoResourceFoundException.class)
    public Result<List<Object>> notResourcexceptionHandling(NoResourceFoundException e) {
        LOGGER.error("找不到访问资源 ", e);
        return Result.fail(notFoundCode, FunctionUtil.getI18nString("result.notFound"));
    }

    /**
     * 处理所有未被其他异常处理器捕获的异常，作为通用的异常处理器。
     * 返回状态码500和错误消息"服务器内部异常,请稍后重试"。
     *
     * @param e Exception 异常对象
     * @return Result<String> 包含错误信息的 Result 对象
     */
    @ExceptionHandler(Exception.class)
    public Result<List<Object>> exceptionHandling(Exception e) {
        LOGGER.error("全局异常 ", e);
        return Result.fail(errorCode, FunctionUtil.getI18nString("result.otherException"));
    }
}
