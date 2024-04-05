package com.tracker.tracker.validators.profile;

import com.tracker.tracker.forms.profile.PasswordChangeForm;
import com.tracker.tracker.services.authentication.AuthenticationService;
import io.micrometer.common.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class PasswordChangeFormValidator implements Validator {
    private final PasswordEncoder encoder;
    private final AuthenticationService authenticationService;

    @Autowired
    public PasswordChangeFormValidator(PasswordEncoder encoder, AuthenticationService authenticationService) {
        this.encoder = encoder;
        this.authenticationService = authenticationService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return PasswordChangeForm.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        PasswordChangeForm form = (PasswordChangeForm) target;

        if (!encoder.matches(form.getOldPassword(), authenticationService.getAuthentication().getPassword())) {
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
