package com.tracker.tracker.services.company;

import com.tracker.tracker.entities.Company;
import com.tracker.tracker.entities.Customer;
import com.tracker.tracker.forms.company.CompanyForm;
import com.tracker.tracker.models.company.CompanyModel;
import com.tracker.tracker.repositories.CompanyRepository;
import com.tracker.tracker.services.customer.CustomerService;
import com.tracker.tracker.services.models.ModelService;
import jakarta.persistence.EntityNotFoundException;
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
    private final ModelService modelService;

    @Autowired
    public CompanyServiceImpl(
            CompanyRepository companyRepository,
            CustomerService customerService,
            ModelService modelService
    ) {
        this.companyRepository = companyRepository;
        this.customerService = customerService;
        this.modelService = modelService;
    }

    @Override
    public Optional<Company> findById(Long id) {
        return companyRepository.findById(id);
    }


    @Override
    public Optional<Company> findByUuid(String uuid) {
        return companyRepository.findCompanyByUuid(uuid);
    }

    @Override
    public void deleteCompanyByUuid(String uuid) {
        companyRepository.deleteByUuid(uuid);
    }

    @Override
    public void deleteCompanyById(Long id) {
        companyRepository.deleteById(id);
    }

    @Override
    public Company getCompanyByUuid(String uuid) {
        return findByUuid(uuid)
                .orElseThrow(() -> new EntityNotFoundException("Компания не найдена"));
    }

    @Override
    public Company getCompanyById(Long id) {
        return findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Компания не найдена"));
    }

    @Override
    public Page<CompanyModel> getCompanies(Pageable pageable, Customer customer) {
        return companyRepository.findAllByCustomer(pageable, customer)
                .map(modelService::getCompanyModel);
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
        return getCompanyByUuid(uuid)
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
    public String generateCompanyUuid() {
        return UUID.randomUUID().toString();
    }
}
