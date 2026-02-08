/**
 * Result 类代表一个带有状态码、消息描述和数据内容的响应对象。
 * 它实现了Serializable接口，可以被序列化和反序列化。
 *
 * @param <T> 泛型参数，代表响应数据的类型
 * @author 裴金伟
 * @version 1.0
 * @since 2024-05-16
 */
package com.api.common.base;

import cn.hutool.extra.spring.SpringUtil;
import com.api.common.config.ResultCodeConfig;
import com.api.common.utils.FunctionUtil;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Result<T> implements Serializable {

    // 序列化版本标识符，用于序列化和反序列化时的版本控制。
    @Serial
    private static final long serialVersionUID = 1L;

    // 状态码，表示请求处理的结果状态，例如HTTP状态码。
    private int code;

    // 消息描述，提供有关响应状态的详细信息。
    private String message;

    // 数据内容，根据不同的业务场景，这里可以包含各种类型的数据。
    private T data;

    private static class ConfigHolder {
        private static final ResultCodeConfig CONFIG = SpringUtil.getBean(ResultCodeConfig.class);
    }

    public static int getSuccessCode() {
        return ConfigHolder.CONFIG.getSuccess();
    }

    public static int getErrorCode() {
        return ConfigHolder.CONFIG.getError();
    }

    public static int getBadRequestCode() {
        return ConfigHolder.CONFIG.getBadRequest();
    }

    /**
     * 构造一个Result对象，包含状态码、消息描述和数据内容。
     *
     * @param code    状态码
     * @param message 消息描述
     * @param data    数据内容
     */
    public Result(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    /**
     * 成功响应构造器，创建一个状态码为200（通常表示成功），
     * 消息描述为"success"的Result对象。
     *
     * @param data 成功时返回的数据
     * @return 新建的Result对象
     */
    public static <T> Result<T> ok(T data) {
        String message = FunctionUtil.getI18nString("result.success");
        return new Result<>(Result.getSuccessCode(), message, data);
    }

    public static <T> Result<List<T>> ok() {
        String message = FunctionUtil.getI18nString("result.success");
        return new Result<>(Result.getSuccessCode(), message, new ArrayList<>());
    }

    public static <T> Result<List<T>> ok(String message) {
        return new Result<>(Result.getSuccessCode(), message, new ArrayList<>());
    }

    public static <T> Result<T> ok(String message, T data) {
        return new Result<>(Result.getSuccessCode(), message, data);
    }

    public static <T> Result<List<T>> fail(String msg) {
        return new Result<>(Result.getBadRequestCode(), msg, new ArrayList<>());
    }

    public static <T> Result<List<T>> fail(int code, String msg) {
        return new Result<>(code, msg, new ArrayList<>());
    }


    /**
     * 获取响应状态码。
     *
     * @return 状态码
     */
    public int getCode() {
        return this.code;
    }

    /**
     * 设置响应状态码。
     *
     * @param code 状态码
     */
    public void setCode(int code) {
        this.code = code;
    }

    /**
     * 获取响应消息描述。
     *
     * @return 消息描述
     */
    public String getMessage() {
        return this.message;
    }

    /**
     * 设置响应消息描述。
     *
     * @param message 消息描述
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * 获取响应数据内容。
     *
     * @return 数据内容
     */
    public T getData() {
        return this.data;
    }

    /**
     * 设置响应数据内容。
     *
     * @param data 数据内容
     */
    public void setData(T data) {
        this.data = data;
    }

    public String toString() {
        return "{\"code\": " + this.getCode() + ", \"msg\": " + this.transValue(this.getMessage()) + ", \"data\": " + this.transValue(this.getData()) + "}";
    }

    private String transValue(Object value) {
        if (value == null) {
            return null;
        } else {
            return value instanceof String ? "\"" + value + "\"" : String.valueOf(value);
        }
    }
}
