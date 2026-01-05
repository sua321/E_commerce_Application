package com.me.e_commerce_application.validator.annotation_implements;

import com.me.e_commerce_application.validator.custom_annotations.AgeVarification;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

public class AgeVerificationValidator implements ConstraintValidator<AgeVarification, String> {
    private int minimumAge;

    @Override
    public void initialize(AgeVarification constraintAnnotation) {
        // Grab the value set in the annotation (default 18)
        this.minimumAge = constraintAnnotation.value();
    }

    @Override
    public boolean isValid(String dobString, ConstraintValidatorContext context) {
        if (dobString == null || dobString.isBlank()) {
            return true; // We return true here to let @NotBlank handle the "required" error
        }

        try {
            // 1. Parse the String to a Date
            // Adjust the pattern "yyyy-MM-dd" to match whatever your frontend sends!
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate dateOfBirth = LocalDate.parse(dobString, formatter);

            // 2. Calculate the age
            LocalDate today = LocalDate.now();
            int age = Period.between(dateOfBirth, today).getYears();

            // 3. Return true if valid, false if not
            return age >= minimumAge;

        } catch (Exception e) {
            // If the date format is wrong, validation fails
            return false;
        }
    }
}
