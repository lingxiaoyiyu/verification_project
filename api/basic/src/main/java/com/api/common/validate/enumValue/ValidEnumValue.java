package com.api.common.validate.enumValue;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ValidEnumValueValidator.class)
@Documented
public @interface ValidEnumValue {
    Class<? extends Enum<?>> enumClass();

    Class<?> expectedType(); // 新增属性

    String message() default "值错误";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

