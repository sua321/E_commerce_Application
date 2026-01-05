package com.me.e_commerce_application.validator.custom_annotations;

import com.me.e_commerce_application.validator.annotation_implements.AgeVerificationValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = AgeVerificationValidator.class) // Link to the logic class below
@Target({ElementType.FIELD}) // Where can we use this? On fields.
@Retention(RetentionPolicy.RUNTIME)
public @interface AgeVarification {
    // The default error message
    String message() default "User must be at least 18 years old";

    // Required boilerplate for Spring Validation
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

    // Allow the user to specify the age (e.g., @MinimumAge(value = 21))
    int value() default 18;
}
