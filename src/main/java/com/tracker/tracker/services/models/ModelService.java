package com.tracker.tracker.services.models;

import com.tracker.tracker.entities.Company;
import com.tracker.tracker.entities.Customer;
import com.tracker.tracker.models.company.CompanyModel;
import com.tracker.tracker.models.main.MainPageModel;
import com.tracker.tracker.models.profile.ProfileModel;

public interface ModelService {
    ProfileModel getProfileModel(Customer customer);
    MainPageModel getMainPageModel(Customer customer);
    CompanyModel getCompanyModel(Company company);
}
