package com.tracker.tracker.validators.profile;

import com.tracker.tracker.forms.profile.AccountInfoChangeForm;
import com.tracker.tracker.services.customer.CustomerService;
import io.micrometer.common.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Objects;

@Component
public class AccountInfoChangeFormValidator implements Validator {
    private final CustomerService customerService;

    @Autowired
    public AccountInfoChangeFormValidator(CustomerService customerService) {
        this.customerService = customerService;
    }

    @Override
    public boolean supports(@NonNull Class<?> clazz) {
        return AccountInfoChangeForm.class.equals(clazz);
    }

    @Override
    public void validate(@NonNull Object target, @NonNull Errors errors) {
        AccountInfoChangeForm form = (AccountInfoChangeForm) target;

        if (StringUtils.isBlank(form.getUsername())) {
            errors.rejectValue("username", "error.account.username.empty");
            return;
        }

        if (StringUtils.isBlank(form.getEmail())) {
            errors.rejectValue("email", "error.account.email.empty");
            return;
        }

        if (!customerService.getAuthenticatedCustomer().getEmail().equals(form.getEmail())) {
            if (customerService.findByEmail(form.getEmail()).isPresent()) {
                errors.rejectValue("email", "error.account.email.exists");
            }
        }

        if (!customerService.getAuthenticatedCustomer().getUsername().equals(form.getUsername())) {
            if (customerService.findByUsername(form.getUsername()).isPresent()) {
                errors.rejectValue("username", "error.account.username.exists");
            }
        }
    }
}
