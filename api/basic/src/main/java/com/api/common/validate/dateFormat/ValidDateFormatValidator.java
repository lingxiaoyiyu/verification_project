package com.api.common.validate.dateFormat;


import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class ValidDateFormatValidator implements ConstraintValidator<ValidDateFormat, String> {

    private String pattern;
    private DateTimeFormatter dateFormatter;

    @Override
    public void initialize(ValidDateFormat constraintAnnotation) {
        this.pattern = constraintAnnotation.pattern();
        this.dateFormatter = DateTimeFormatter.ofPattern(pattern);
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            return true; // 可以根据需求调整对null值的处理
        }
        try {
            dateFormatter.parse(value); // 尝试解析日期
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }
}

