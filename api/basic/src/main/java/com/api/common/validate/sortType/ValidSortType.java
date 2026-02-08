package com.api.common.validate.sortType;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = ValidSortTypeValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidSortType {
    String message() default "排序类型只能是 'ASC' 或 'DESC'";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
