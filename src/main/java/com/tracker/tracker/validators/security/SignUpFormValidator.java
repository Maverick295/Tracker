package com.tracker.tracker.validators.security;

import com.tracker.tracker.forms.security.SignUpForm;
import com.tracker.tracker.utils.validate.RegistrationUtil;
import io.micrometer.common.lang.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class SignUpFormValidator implements Validator {
    private final RegistrationUtil registrationUtil;

    @Autowired
    public SignUpFormValidator(
            RegistrationUtil registrationUtil
    ) {
        this.registrationUtil = registrationUtil;
    }

    @Override
    public boolean supports(@NonNull Class<?> clazz) {
        return SignUpForm.class.equals(clazz);
    }

    @Override
    public void validate(@NonNull Object target, @NonNull Errors errors) {
        SignUpForm form = (SignUpForm) target;

        if (!registrationUtil.validateUsername(form.getUsername())) {
            errors.rejectValue(
                    "username",
                    "error.signup.username.valid"
            );
        }

        if (!registrationUtil.validateEmail(form.getEmail())) {
            errors.rejectValue(
                    "email",
                    "error.signup.email.valid"
            );
        }
    }
}
