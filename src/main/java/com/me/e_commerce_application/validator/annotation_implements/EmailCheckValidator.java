package com.me.e_commerce_application.validator.annotation_implements;

import com.me.e_commerce_application.validator.custom_annotations.EmailCheck;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

@Component
@AllArgsConstructor
public class EmailCheckValidator implements ConstraintValidator <EmailCheck, String> {
    private final Pattern email_pattern;


    @Override
    public boolean isValid(String email, ConstraintValidatorContext constraintValidatorContext) {if(email == null)
            return true;
        return email_pattern.matcher(email).matches();
    }
}
