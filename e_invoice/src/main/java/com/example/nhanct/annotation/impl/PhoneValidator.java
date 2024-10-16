package com.example.nhanct.annotation.impl;

import com.example.nhanct.annotation.Phone;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;



public class PhoneValidator implements
        ConstraintValidator<Phone, String> {

    @Override
    public void initialize(Phone contactNumber) {
        System.out.println("initialize : PhoneValidator");
    }

    @Override
    public boolean isValid(String field,
                           ConstraintValidatorContext cxt) {
        return field != null && field.matches("[0-9]+")
                && (field.length() == 10);
    }

}