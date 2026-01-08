package com.me.e_commerce_application.validator.custom_annotations;

import com.me.e_commerce_application.validator.annotation_implements.EmailCheckValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = EmailCheckValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface EmailCheck {
    String message() default "Invalid email format";

    // Required boilerplate for Spring Validation
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}
