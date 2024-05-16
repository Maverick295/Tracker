package com.tracker.tracker.validators.profile;

import com.tracker.tracker.forms.profile.PersonalInfoChangeForm;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class PersonalInfoChangeFormValidator implements Validator {
    @Override
    public boolean supports(@NonNull Class<?> clazz) {
        return PersonalInfoChangeForm.class.equals(clazz);
    }

    @Override
    public void validate(@NonNull Object target, @NonNull Errors errors) {
        PersonalInfoChangeForm form = (PersonalInfoChangeForm) target;
    }
}
