package com.api.common.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

/**
 * JsonUtil 是一个实用类，提供了将Java对象与JSON字符串之间进行转换的方法。
 * 它使用Jackson库的ObjectMapper进行序列化和反序列化操作。
 *
 * @author 裴金伟
 * @version 1.0
 * @since 2024-05-16
 */
public class JsonUtil {

    /**
     * ObjectMapper 实例，用于执行JSON操作。
     */
    private static final ObjectMapper objectMapper = new ObjectMapper();

    /**
     * 将给定的Java对象转换为JSON格式的字符串。
     *
     * @param obj 待序列化的Java对象。
     * @return 一个JSON格式的字符串，表示输入的Java对象。
     * @throws RuntimeException 如果转换过程中发生错误，包装了JsonProcessingException。
     */
    public static String toJson(Object obj) {
        try {
            return objectMapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to convert object to JSON", e);
        }
    }

    /**
     * 将JSON字符串反序列化为指定类型的Java对象。
     *
     * @param jsonStr JSON格式的字符串。
     * @param toClass 目标Java对象的类类型。
     * @param <T>     泛型参数，表示目标Java对象的类型。
     * @return 反序列化后的Java对象。
     * @throws RuntimeException 如果反序列化过程中发生错误，包装了JsonProcessingException。
     */
    public static <T> T toObject(String jsonStr, Class<T> toClass) {
        try {
            return objectMapper.readValue(jsonStr, toClass);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to convert JSON to object", e);
        }
    }

    /**
     * 将JSON字符串反序列化为指定元素类型的Java List对象。
     *
     * @param jsonStr     JSON格式的字符串，表示一个列表。
     * @param elementType 列表中元素的类类型。
     * @param <T>         泛型参数，表示列表中元素的类型。
     * @return 反序列化后的Java List对象，包含指定类型的元素。
     * @throws RuntimeException 如果反序列化过程中发生错误，包装了JsonProcessingException。
     */
    public static <T> List<T> jsonToList(String jsonStr, Class<T> elementType) {
        try {
            JavaType javaType = objectMapper.getTypeFactory().constructParametricType(List.class, elementType);
            return objectMapper.readValue(jsonStr, javaType);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to convert JSON string to list", e);
        }
    }
}
