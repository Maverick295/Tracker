package com.tracker.tracker.validators.profile;

import com.tracker.tracker.forms.profile.PersonalInfoChangeForm;
import com.tracker.tracker.utils.validate.PersonalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class PersonalInfoChangeFormValidator implements Validator {
    private final PersonalUtil personalUtil;

    @Autowired
    public PersonalInfoChangeFormValidator(PersonalUtil personalUtil) {
        this.personalUtil = personalUtil;
    }

    @Override
    public boolean supports(@NonNull Class<?> clazz) {
        return PersonalInfoChangeForm.class.equals(clazz);
    }

    @Override
    public void validate(@NonNull Object target, @NonNull Errors errors) {
        PersonalInfoChangeForm form = (PersonalInfoChangeForm) target;

        if (personalUtil.validatePhone(form.getPhone())) {
            errors.rejectValue(
                    "phone",
                    "error.personal.phone.valid"
            );
        }
    }
}
