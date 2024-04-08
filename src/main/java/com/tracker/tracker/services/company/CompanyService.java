package com.tracker.tracker.services.company;

import com.tracker.tracker.entities.Company;
import com.tracker.tracker.forms.company.CompanyChangeForm;
import com.tracker.tracker.forms.company.CompanyCreateForm;

public interface CompanyService {
    Company createCompany(CompanyCreateForm form);
    void save(Company company);
    Company editCompany(CompanyChangeForm form);
    void deleteCompany(Long id);
}
