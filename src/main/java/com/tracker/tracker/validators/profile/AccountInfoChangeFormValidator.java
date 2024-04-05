package com.tracker.tracker.validators.profile;

import com.tracker.tracker.forms.profile.AccountInfoChangeForm;
import com.tracker.tracker.services.authentication.AuthenticationService;
import com.tracker.tracker.services.customer.CustomerService;
import io.micrometer.common.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Objects;

@Component
public class AccountInfoChangeFormValidator implements Validator {
    private final AuthenticationService authenticationService;
    private final CustomerService customerService;

    @Autowired
    public AccountInfoChangeFormValidator(AuthenticationService authenticationService, CustomerService customerService) {
        this.authenticationService = authenticationService;
        this.customerService = customerService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return AccountInfoChangeForm.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        AccountInfoChangeForm form = (AccountInfoChangeForm) target;

        if (!authenticationService.getAuthentication().getEmail().equals(form.getEmail())) {
            if (Objects.nonNull(customerService.findByEmail(form.getEmail()))) {
                errors.rejectValue("email", "error.account.email.exists");
            }
        }

        if (!authenticationService.getAuthentication().getUsername().equals(form.getUsername())) {
            if (Objects.nonNull(customerService.findByUsername(form.getUsername()))) {
                errors.rejectValue("username", "error.account.username.exists");
            }
        }

        if (StringUtils.isBlank(form.getUsername())) {
            errors.rejectValue("username", "error.account.username.empty");
        }

        if (StringUtils.isBlank(form.getEmail())) {
            errors.rejectValue("email", "error.account.email.empty");
        }
    }
}
