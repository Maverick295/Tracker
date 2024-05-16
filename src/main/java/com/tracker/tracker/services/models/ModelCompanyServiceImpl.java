package com.tracker.tracker.services.models;

import com.tracker.tracker.entities.Company;
import com.tracker.tracker.models.company.CompanyModel;
import org.springframework.stereotype.Service;

@Service
public class ModelCompanyServiceImpl implements ModelCompanyService {

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
