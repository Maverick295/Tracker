package com.tracker.tracker.services.company;

import com.tracker.tracker.entities.Company;
import com.tracker.tracker.entities.Customer;
import com.tracker.tracker.forms.company.CompanyChangeForm;
import com.tracker.tracker.forms.company.CompanyCreateForm;
import com.tracker.tracker.models.company.CompanyModel;
import com.tracker.tracker.repositories.CompanyRepository;
import com.tracker.tracker.services.authentication.AuthenticationService;
import com.tracker.tracker.services.models.ModelCompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class CompanyServiceImpl implements CompanyService {
    private final CompanyRepository companyRepository;
    private final AuthenticationService authenticationService;
    private final ModelCompanyService modelCompanyService;

    @Autowired
    public CompanyServiceImpl(CompanyRepository companyRepository, AuthenticationService authenticationService, ModelCompanyService modelCompanyService) {
        this.companyRepository = companyRepository;
        this.authenticationService = authenticationService;
        this.modelCompanyService = modelCompanyService;
    }

    @Override
    public Company createCompany(CompanyCreateForm form) {
        LocalDateTime localDateTime = LocalDateTime.now();
        Customer ownerCustomer = authenticationService.getAuthentication();
        return new Company()
                .setCompanyName(form.getCompanyName())
                .setLegalEntity(form.getLegalEntity())
                .setInn(form.getInn())
                .setBankAccount(form.getBankAccount())
                .setBankBik(form.getBankBik())
                .setLegalAddress(form.getLegalAddress())
                .setActualAddress(form.getActualAddress())
                .setEmail(form.getEmail())
                .setPhoneNumber(form.getPhoneNumber())
                .setDirectorFullName(form.getDirectorFullName())
                .setOgrn(form.getOgrn())
                .setOkpo(form.getOkpo())
                .setDateOfCreate(localDateTime)
                .setCustomer(ownerCustomer);
    }

    @Override
    public void save(Company company) {
        companyRepository.save(company);
    }

    @Override
    public Company editCompany(CompanyChangeForm form) {
        Company company = companyRepository.findById(form.getCompanyId()).orElse(null);
        if (company != null) {
            company.setCompanyName(form.getCompanyName());
            company.setLegalEntity(form.getLegalEntity());
            company.setInn(form.getInn());
            company.setBankAccount(form.getBankAccount());
            company.setBankBik(form.getBankBik());
            company.setLegalAddress(form.getLegalAddress());
            company.setActualAddress(form.getActualAddress());
            company.setEmail(form.getEmail());
            company.setPhoneNumber(form.getPhoneNumber());

            return company;
        }
        return null; // Придмуать, что возвращать
    }

    @Override
    public void deleteCompany(Long id) {
        companyRepository.deleteById(id);
    }

    @Override
    public Optional<Company> findById(Long id) {
        Optional<Company> company = companyRepository.findById(id);
        return company;
    }

    @Override
    public Page<CompanyModel> getCompanies(Pageable pageable) {
        Customer customer = authenticationService.getAuthentication();
        return companyRepository.findAllByCustomer(pageable, customer)
                .map(company -> modelCompanyService.getCompanyModel(company));
    }
}
