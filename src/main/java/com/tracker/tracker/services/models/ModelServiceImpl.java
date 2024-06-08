package com.tracker.tracker.services.models;

import com.tracker.tracker.entities.Company;
import com.tracker.tracker.entities.Customer;
import com.tracker.tracker.models.company.CompanyModel;
import com.tracker.tracker.models.main.MainPageModel;
import com.tracker.tracker.models.profile.ProfileModel;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class ModelServiceImpl implements ModelService {

    @Override
    public ProfileModel getProfileModel(Customer customer) {
        return new ProfileModel()
                .setEmail(customer.getEmail())
                .setName(customer.getName())
                .setSurname(customer.getSurname())
                .setPhone(customer.getPhone())
                .setUsername(customer.getUsername());
    }

    @Override
    public MainPageModel getMainPageModel(Customer customer) {
        return new MainPageModel()
                .setUsername(customer.getUsername());
    }

    @Override
    public CompanyModel getCompanyModel(Company company) {
        return new CompanyModel()
                .setCompanyName(company.getCompanyName())
                .setEmail(company.getEmail())
                .setActualAddress(company.getActualAddress())
                .setBankBik(company.getBankBik())
                .setOgrn(company.getOgrn())
                .setOkpo(company.getOkpo())
                .setLegalAddress(company.getLegalAddress())
                .setInn(company.getInn())
                .setBankAccount(company.getBankAccount())
                .setPhoneNumber(company.getPhoneNumber())
                .setLegalEntity(company.getLegalEntity())
                .setUuid(company.getUuid())
                .setKpp(company.getKpp())
                .setBankInn(company.getBankInn())
                .setBankKpp(company.getBankKpp())
                .setBankName(company.getBankName())
                .setCorrespondentAccount(company.getCorrespondentAccount())
                .setKpp(company.getKpp())
                .setDirectorName(company.getDirectorName())
                .setDirectorSurname(company.getDirectorSurname())
                .setDirectorPatronymic(company.getDirectorPatronymic())
                .setOgrnip(company.getOgrnip());
    }
}
