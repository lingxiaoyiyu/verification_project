package com.api.common.validate.phoneNumber;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ValidPhoneNumberValidator implements ConstraintValidator<ValidPhoneNumber, String> {

    private static final String PHONE_PATTERN = "^1[3-9]\\d{9}$"; // 基于中国手机号码规则的正则表达式
    private boolean nullable;

    @Override
    public void initialize(ValidPhoneNumber constraintAnnotation) {
        this.nullable = constraintAnnotation.nullable();
    }

    @Override
    public boolean isValid(String phoneNumber, ConstraintValidatorContext context) {
        if (phoneNumber == null) {
            return nullable;
        }
        if (phoneNumber.isEmpty()) {
            return true; // 允许空字符串
        }
        return phoneNumber.matches(PHONE_PATTERN);
    }
}
