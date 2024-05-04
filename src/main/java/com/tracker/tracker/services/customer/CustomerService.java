package com.tracker.tracker.services.customer;

import com.tracker.tracker.entities.Customer;
import com.tracker.tracker.forms.security.SignUpForm;

public interface CustomerService {
    void save(Customer customer);
    Customer findByUsername(String username);
    Customer findByEmail(String email);
    Customer createCustomer(SignUpForm form);
    String generateUserUuid();
    Customer getAuthenticatedCustomer();
    void saveCustomerAndUpdateSession(Customer customer);
}
