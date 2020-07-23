package com.robo.student.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class StudentIdValidator implements ConstraintValidator<ValidStudentId, String> {

    @Override
    public void initialize(ValidStudentId constraintAnnotation) {
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {

        if (value.length() > 8){
            return false;
        }

        try {
            Integer.parseInt(value);
        } catch (NumberFormatException nfe) {
            return false;
        }

        return true;
    }
}
