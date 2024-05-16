package com.tracker.tracker.validators.company;

import com.tracker.tracker.forms.company.CompanyForm;
import com.tracker.tracker.services.company.CompanyService;
import io.micrometer.common.lang.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class CompanyFormValidator implements Validator {
    private final CompanyService companyService;

    @Autowired
    public CompanyFormValidator(CompanyService companyService) {
        this.companyService = companyService;
    }

    @Override
    public boolean supports(@NonNull Class<?> clazz) {
        return CompanyForm.class.equals(clazz);
    }

    @Override
    public void validate(@NonNull Object target, @NonNull Errors errors) {
        CompanyForm form = (CompanyForm) target;
        // Написать проверки
    }
}
