package com.api.common.validate.dateFormat;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ValidDateFormatValidator.class)
@Documented
public @interface ValidDateFormat {
    // 默认的日期时间格式
    String pattern() default "yyyy-MM-dd HH:mm:ss";

    String message() default "日期格式不正确";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
