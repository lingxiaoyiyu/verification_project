package com.api.common.exception;

/**
 * ServerException 表示在服务端发生的一类运行时异常。
 * 此类通常用于封装那些不可预见的、需要立即处理的错误情况。
 * 当服务端遇到无法正常处理的情况时，可以通过抛出 ServerException 来通知调用者。
 */
public class ServerException extends RuntimeException {

    /**
     * 创建一个 ServerException 实例，包含错误信息。
     *
     * @param message 描述异常情况的详细信息
     */
    public ServerException(String message) {
        super(message);
    }

    /**
     * 创建一个 ServerException 实例，包含错误信息和原始异常原因。
     *
     * @param message 描述异常情况的详细信息
     * @param cause   原始异常，导致当前 ServerException 发生的原因
     */
    public ServerException(String message, Throwable cause) {
        super(message, cause);
    }
}
