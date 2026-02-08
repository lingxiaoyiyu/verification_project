package com.api.common.validate.listNotEmpty;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.List;

public class ValidListNotEmptyValidator implements ConstraintValidator<ValidListNotEmpty, List<?>> {

    @Override
    public void initialize(ValidListNotEmpty constraintAnnotation) {
        // 初始化方法，通常不需要做任何事情，除非你有特定的初始化逻辑
    }

    @Override
    public boolean isValid(List<?> list, ConstraintValidatorContext context) {
        // 校验逻辑：检查List是否为空
        return list != null && !list.isEmpty();
    }
}

