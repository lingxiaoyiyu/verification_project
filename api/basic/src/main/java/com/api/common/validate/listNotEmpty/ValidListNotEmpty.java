package com.api.common.validate.listNotEmpty;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ValidListNotEmptyValidator.class)
public @interface ValidListNotEmpty {
    String message() default "列表不能为空";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
