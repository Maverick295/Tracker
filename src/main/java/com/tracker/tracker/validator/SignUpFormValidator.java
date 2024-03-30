package com.tracker.tracker.validator;

import com.tracker.tracker.form.SignUpForm;
import com.tracker.tracker.service.customer.CustomerService;
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
    public boolean supports(Class<?> clazz) {
        return SignUpForm.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        SignUpForm form = (SignUpForm) target;

        if (Objects.nonNull(customerService.findByUsername(form.getUsername()))) {
            errors.rejectValue("username", "error.signup.username.exist");
        }

        if (Objects.nonNull(customerService.findByEmail(form.getEmail()))) {
            errors.rejectValue("email", "error.signup.email.exist");
        }
    }
}
