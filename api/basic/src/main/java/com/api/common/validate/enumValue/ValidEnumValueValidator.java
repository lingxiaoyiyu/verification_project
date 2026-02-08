package com.api.common.validate.enumValue;


import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ValidEnumValueValidator implements ConstraintValidator<ValidEnumValue, Object> {

    private Class<? extends Enum<?>> enumClass;
    private Class<?> expectedType;

    @Override
    public void initialize(ValidEnumValue constraintAnnotation) {
        this.enumClass = constraintAnnotation.enumClass();
        this.expectedType = constraintAnnotation.expectedType();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }

        if (!expectedType.isInstance(value)) {
            return false;
        }

        try {
            // 假设枚举中有静态方法 fromCode
            // 根据expectedType调用不同的方法
            if (expectedType.equals(Integer.class)) {
                Enum<?> fromCode = (Enum<?>) enumClass.getMethod("fromCode", Integer.class).invoke(null, value);
                return true;
            } else if (expectedType.equals(String.class)) {
                Enum<?> fromName = (Enum<?>) enumClass.getMethod("fromName", String.class).invoke(null, value);
                return true;
            } else {
                throw new IllegalArgumentException("不支持的类型： " + expectedType);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}