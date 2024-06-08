package com.tracker.tracker.services.customer;

import com.tracker.tracker.entities.Customer;
import com.tracker.tracker.forms.security.SignUpForm;

import java.util.Optional;

public interface CustomerService {
    void save(Customer customer);
    Optional<Customer> findByUsername(String username);
    Optional<Customer> findByEmail(String email);
    Customer createCustomer(SignUpForm form);
    String generateUserUuid();
    Customer getCustomerByUsername(String username);
    Customer getCustomerByEmail(String email);
    Customer getAuthenticatedCustomer();
}
