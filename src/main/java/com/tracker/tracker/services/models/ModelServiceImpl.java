package com.tracker.tracker.services.models;

import com.tracker.tracker.entities.Customer;
import com.tracker.tracker.models.main.MainPageModel;
import com.tracker.tracker.models.profile.ProfileModel;
import org.springframework.stereotype.Service;

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
        if (customer != null) {
            return new MainPageModel()
                    .setUsername(customer.getUsername());
        }
        return new MainPageModel();
    }
}
