package com.tracker.tracker.validators.profile;

import com.tracker.tracker.forms.profile.PasswordChangeForm;
import com.tracker.tracker.services.customer.CustomerService;
import io.micrometer.common.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class PasswordChangeFormValidator implements Validator {
    private final PasswordEncoder encoder;
    private final CustomerService customerService;

    @Autowired
    public PasswordChangeFormValidator(
            PasswordEncoder encoder,
            CustomerService customerService
    ) {
        this.encoder = encoder;
        this.customerService = customerService;
    }

    @Override
    public boolean supports(@NonNull Class<?> clazz) {
        return PasswordChangeForm.class.equals(clazz);
    }

    @Override
    public void validate(@NonNull Object target, @NonNull Errors errors) {
        PasswordChangeForm form = (PasswordChangeForm) target;

        if (!encoder.matches(form.getOldPassword(), customerService.getAuthenticatedCustomer().getPassword())) {
            errors.rejectValue("oldPassword", "error.password.old.invalid");
        }

        if (!encoder.matches(form.getConfirmPassword(), encoder.encode(form.getNewPassword()))) {
            errors.rejectValue("confirmPassword", "error.password.new.mismatch");
        }

        if (encoder.matches(form.getOldPassword(), encoder.encode(form.getNewPassword()))) {
            errors.rejectValue("oldPassword", "error.password.old.new.coincide");
        }

        if (StringUtils.isBlank(form.getOldPassword())) {
            errors.rejectValue("oldPassword", "error.password.old.empty");
        }

        if (StringUtils.isBlank(form.getNewPassword())) {
            errors.rejectValue("newPassword", "error.password.new.empty");
        }

        if (StringUtils.isBlank(form.getConfirmPassword())) {
            errors.rejectValue("confirmPassword", "error.password.confirm.empty");
        }
    }
}
