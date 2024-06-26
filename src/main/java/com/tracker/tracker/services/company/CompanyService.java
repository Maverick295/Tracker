package com.tracker.tracker.services.company;

import com.tracker.tracker.entities.Company;
import com.tracker.tracker.entities.Customer;
import com.tracker.tracker.forms.company.CompanyForm;
import com.tracker.tracker.models.company.CompanyModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface CompanyService {
    Optional<Company> findByUuid(String uuid);
    void deleteCompanyByUuid(String uuid);
    Company getCompanyByUuid(String uuid);
    Company createCompany(CompanyForm form);
    void save(Company company);
    Company editCompany(CompanyForm form, String uuid);
    Page<CompanyModel> getCompanies(Pageable pageable, Customer customer);
    String generateCompanyUuid();
}
