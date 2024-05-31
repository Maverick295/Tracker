package com.tracker.tracker.services.models;

import com.tracker.tracker.entities.Company;
import com.tracker.tracker.models.company.CompanyModel;

public interface ModelCompanyService {
    CompanyModel getCompanyModel(Company company);
}
