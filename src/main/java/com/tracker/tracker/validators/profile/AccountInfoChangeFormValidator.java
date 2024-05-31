package com.tracker.tracker.validators.profile;

import com.tracker.tracker.forms.profile.AccountInfoChangeForm;
import com.tracker.tracker.utils.validate.AccountUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class AccountInfoChangeFormValidator implements Validator {
    private final AccountUtil accountUtil;

    @Autowired
    public AccountInfoChangeFormValidator(AccountUtil accountUtil) {
        this.accountUtil = accountUtil;
    }

    @Override
    public boolean supports(@NonNull Class<?> clazz) {
        return AccountInfoChangeForm.class.equals(clazz);
    }

    @Override
    public void validate(@NonNull Object target, @NonNull Errors errors) {
        AccountInfoChangeForm form = (AccountInfoChangeForm) target;

        if (!accountUtil.validChangedUsername(form.getUsername())) {
            errors.rejectValue(
                    "username",
                    "error.account.username.valid"
            );
        }

        if (accountUtil.validChangedEmail(form.getEmail())) {
            errors.rejectValue(
                    "email",
                    "error.account.email.valid"
            );
        }
    }
}
