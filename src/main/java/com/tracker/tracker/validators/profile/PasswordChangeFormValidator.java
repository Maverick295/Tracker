package com.tracker.tracker.validators.profile;

import com.tracker.tracker.forms.profile.PasswordChangeForm;
import com.tracker.tracker.utils.validate.PasswordUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class PasswordChangeFormValidator implements Validator {
    private final PasswordUtil passwordUtil;

    @Autowired
    public PasswordChangeFormValidator(PasswordUtil passwordUtil) {
        this.passwordUtil = passwordUtil;
    }

    @Override
    public boolean supports(@NonNull Class<?> clazz) {
        return PasswordChangeForm.class.equals(clazz);
    }

    @Override
    public void validate(@NonNull Object target, @NonNull Errors errors) {
        PasswordChangeForm form = (PasswordChangeForm) target;

        if (!passwordUtil.validateOldPassword(form.getOldPassword(), form.getNewPassword())) {
            errors.rejectValue(
                    "oldPassword",
                    "error.password.oldPassword.valid"
            );
        }

        if (!passwordUtil.validateNewPassword(form.getNewPassword())) {
            errors.rejectValue(
                    "newPassword",
                    "error.password.newPassword.valid"
            );
        }

        if (!passwordUtil.validateConfirmPassword(form.getNewPassword(), form.getConfirmPassword())) {
            errors.rejectValue(
                    "confirmPassword",
                    "error.password.confirmPassword.valid"
            );
        }
    }
}
