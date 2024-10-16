package com.example.nhanct.annotation;

import com.example.nhanct.annotation.impl.PhoneValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;


@Documented
@Constraint(validatedBy = PhoneValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface Phone {
    String message() default "Invalid phone number, phone number must be 10 digits and not contain characters!";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}