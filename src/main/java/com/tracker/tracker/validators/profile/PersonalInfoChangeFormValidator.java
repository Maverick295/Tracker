package com.tracker.tracker.validators.profile;

import com.tracker.tracker.forms.profile.PersonalInfoChangeForm;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class PersonalInfoChangeFormValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return PersonalInfoChangeForm.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        PersonalInfoChangeForm form = (PersonalInfoChangeForm) target;
    }
}
