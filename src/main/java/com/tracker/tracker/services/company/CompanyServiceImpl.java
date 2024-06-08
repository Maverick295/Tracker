package com.tracker.tracker.services.company;

import com.tracker.tracker.entities.Company;
import com.tracker.tracker.entities.Customer;
import com.tracker.tracker.forms.company.CompanyForm;
import com.tracker.tracker.models.company.CompanyModel;
import com.tracker.tracker.repositories.CompanyRepository;
import com.tracker.tracker.services.customer.CustomerService;
import com.tracker.tracker.services.models.ModelCompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
public class CompanyServiceImpl implements CompanyService {
    private final CompanyRepository companyRepository;
    private final CustomerService customerService;
    private final ModelCompanyService modelCompanyService;

    @Autowired
    public CompanyServiceImpl(CompanyRepository companyRepository,  CustomerService customerService, ModelCompanyService modelCompanyService) {
        this.companyRepository = companyRepository;
        this.customerService = customerService;
        this.modelCompanyService = modelCompanyService;
    }

    @Override
    public Company createCompany(CompanyForm form) {
        LocalDateTime localDateTime = LocalDateTime.now();
        Customer ownerCustomer = customerService.getAuthenticatedCustomer();
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
                .setOgrn(form.getOgrn())
                .setOkpo(form.getOkpo())
                .setDateOfCreate(localDateTime)
                .setCustomer(ownerCustomer)
                .setUuid(generateCompanyUuid())
                .setKpp(form.getKpp())
                .setBankInn(form.getBankInn())
                .setBankKpp(form.getBankKpp())
                .setBankName(form.getBankName())
                .setCorrespondentAccount(form.getCorrespondentAccount())
                .setKpp(form.getKpp())
                .setDirectorName(form.getDirectorName())
                .setDirectorSurname(form.getDirectorSurname())
                .setDirectorPatronymic(form.getDirectorPatronymic())
                .setOgrnip(form.getOgrnip());
    }

    @Override
    public void save(Company company) {
        companyRepository.save(company);
    }

    @Override
    public Company editCompany(CompanyForm form, String uuid) {
        Optional<Company> optionalCompany = companyRepository.findCompanyByUuid(uuid);
        Company company = optionalCompany.orElse(null);

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
            company.setOgrn(form.getOgrn());
            company.setOkpo(form.getOkpo());
            company.setKpp(form.getKpp());
            company.setBankInn(form.getBankInn());
            company.setBankKpp(form.getBankKpp());
            company.setBankName(form.getBankName());
            company.setCorrespondentAccount(form.getCorrespondentAccount());
            company.setKpp(form.getKpp());
            company.setDirectorName(form.getDirectorName());
            company.setDirectorSurname(form.getDirectorSurname());
            company.setDirectorPatronymic(form.getDirectorPatronymic());
            company.setOgrnip(form.getOgrnip());

            return company;
        }
        return null; // Придмуать, что возвращать
    }

    @Override
    public void deleteCompanyById(Long id) {

    }

    @Override
    public Optional<Company> findById(Long id) {
        Optional<Company> company = companyRepository.findById(id);
        return company;
    }

    @Override
    public Page<CompanyModel> getCompanies(Pageable pageable, Customer customer) {
        return companyRepository.findAllByCustomer(pageable, customer)
                .map(company -> modelCompanyService.getCompanyModel(company));
    }

    @Override
    public String generateCompanyUuid() {
        return UUID.randomUUID().toString();
    }

    @Override
    public Optional<Company> findByUuid(String uuid) {
        Optional<Company> company = companyRepository.findCompanyByUuid(uuid);
        return company;
    }

    @Override
    public void deleteCompanyByUuid(String uuid) {
        companyRepository.deleteByUuid(uuid);
    }
}
