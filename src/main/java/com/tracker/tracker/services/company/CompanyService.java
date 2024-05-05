package com.tracker.tracker.services.company;

import com.tracker.tracker.entities.Company;
import com.tracker.tracker.forms.company.CompanyChangeForm;
import com.tracker.tracker.forms.company.CompanyCreateForm;
import com.tracker.tracker.models.company.CompanyModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.Optional;

public interface CompanyService {
    Company createCompany(CompanyCreateForm form);
    void save(Company company);
    Company editCompany(CompanyChangeForm form);
    void deleteCompany(Long id);
    Optional<Company> findById(Long id);
    Page<CompanyModel> getCompanies(Pageable pageable);
    String generateCompanyUuid();
    Optional<Company> findByUuid(String uuid);
    void deleteCompanyByUuid(String uuid);
}
