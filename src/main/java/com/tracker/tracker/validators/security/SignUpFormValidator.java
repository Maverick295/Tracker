package com.tracker.tracker.validators.security;

import com.tracker.tracker.forms.security.SignUpForm;
import com.tracker.tracker.services.customer.CustomerService;
import io.micrometer.common.lang.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Objects;

@Component
public class SignUpFormValidator implements Validator {
    private final CustomerService customerService;

    @Autowired
    public SignUpFormValidator(CustomerService customerService) {
        this.customerService = customerService;
    }

    @Override
    public boolean supports(@NonNull Class<?> clazz) {
        return SignUpForm.class.equals(clazz);
    }

    @Override
    public void validate(@NonNull Object target, @NonNull Errors errors) {
        SignUpForm form = (SignUpForm) target;

        if (customerService.findByUsername(form.getUsername()).isPresent()) {
            errors.rejectValue("username", "error.signup.username.exist");
        }

        if (customerService.findByEmail(form.getEmail()).isPresent()) {
            errors.rejectValue("email", "error.signup.email.exist");
        }
    }
}
