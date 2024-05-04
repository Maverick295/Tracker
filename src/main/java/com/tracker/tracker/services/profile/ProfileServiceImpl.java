package com.tracker.tracker.services.profile;

import com.tracker.tracker.entities.Customer;
import com.tracker.tracker.forms.profile.AccountInfoChangeForm;
import com.tracker.tracker.forms.profile.PasswordChangeForm;
import com.tracker.tracker.forms.profile.PersonalInfoChangeForm;
import com.tracker.tracker.services.customer.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class ProfileServiceImpl implements ProfileService {
    private final CustomerService customerService;
    private final PasswordEncoder encoder;

    @Autowired
    public ProfileServiceImpl(
            CustomerService customerService, 
            PasswordEncoder encoder
    ) {
        this.customerService = customerService;
        this.encoder = encoder;
    }

    @Override
    public Customer changePersonalInfo(PersonalInfoChangeForm form) {
        return customerService.getAuthenticatedCustomer()
                .setName(form.getName())
                .setSurname(form.getSurname())
                .setPhone(form.getPhone());
    }

    @Override
    public Customer changeAccountInfo(AccountInfoChangeForm form) {
        return customerService.getAuthenticatedCustomer()
                .setEmail(form.getEmail())
                .setUsername(form.getUsername());
    }

    @Override
    public Customer changePasswordInfo(PasswordChangeForm form) {
        return customerService.getAuthenticatedCustomer()
                .setPassword(encoder.encode(form.getNewPassword()));
    }
}
