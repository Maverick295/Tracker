package com.tracker.tracker.service.customer;

import com.tracker.tracker.entity.Customer;
import com.tracker.tracker.form.SignUpForm;

import java.util.Optional;

public interface CustomerService {
    void save(Customer customer);
    Customer findByUsername(String username);
    Customer findByEmail(String email);
    Customer createCustomer(SignUpForm form);
}
