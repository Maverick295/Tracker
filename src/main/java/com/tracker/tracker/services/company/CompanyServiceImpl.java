package com.tracker.tracker.services.company;

import com.tracker.tracker.entities.Company;
import com.tracker.tracker.forms.company.CompanyChangeForm;
import com.tracker.tracker.forms.company.CompanyCreateForm;
import com.tracker.tracker.repositories.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompanyServiceImpl implements CompanyService {
    private final CompanyRepository companyRepository;

    @Autowired
    public CompanyServiceImpl(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @Override
    public Company createCompany(CompanyCreateForm form) {
        return null;
    }

    @Override
    public void save(Company company) {

    }

    @Override
    public Company editCompany(CompanyChangeForm form) {
        return null;
    }

    @Override
    public void deleteCompany(Long id) {

    }
}
