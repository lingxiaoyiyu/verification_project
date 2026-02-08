package com.api.common.validate.sortType;

import cn.hutool.core.util.StrUtil;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ValidSortTypeValidator implements ConstraintValidator<ValidSortType, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (StrUtil.isEmpty(value)) {
            // 如果字段可以为null，这里直接返回true，根据实际需求调整
            return true;
        }
        return "ASC".equalsIgnoreCase(value) || "DESC".equalsIgnoreCase(value);
    }
}