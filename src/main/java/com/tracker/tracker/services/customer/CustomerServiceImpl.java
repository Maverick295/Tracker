package com.tracker.tracker.services.customer;

import com.tracker.tracker.entities.Customer;
import com.tracker.tracker.entities.role.Role;
import com.tracker.tracker.forms.security.SignUpForm;
import com.tracker.tracker.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private final PasswordEncoder encoder;

    @Autowired
    public CustomerServiceImpl(
            CustomerRepository customerRepository,
            PasswordEncoder encoder
    ) {
        this.customerRepository = customerRepository;
        this.encoder = encoder;
    }

    @Override
    public void save(Customer customer) {
        customerRepository.save(customer);
    }

    @Override
    public Customer findByUsername(String username) {
        return customerRepository.findByUsername(username);
    }

    @Override
    public Customer findByEmail(String email) {
        return customerRepository.findByEmail(email);
    }

    @Override
    public String generateUserUuid() {
        return UUID.randomUUID().toString();
    }

    @Override
    public Customer createCustomer(SignUpForm form) {
        LocalDateTime localDateTime = LocalDateTime.now();

        return new Customer()
                .setUuid(generateUserUuid())
                .setUsername(form.getUsername())
                .setEmail(form.getEmail())
                .setPassword(encoder.encode(form.getPassword()))
                .setDateOfCreate(localDateTime)
                .setActive(true)
                .setRole(Role.USER.getAuthority());
    }

    @Override
    public Customer getAuthenticatedCustomer() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        return findByUsername(authentication.getName());
    }

    @Override
    public void saveCustomerAndUpdateSession(Customer customer) {
        if (customer == null) {
            throw new IllegalArgumentException("'customer' == null");
        }

        try {
            save(customer);

        } catch (Exception e) {
            throw new RuntimeException("Ошибка во время обновления сессии: ", e);
        }
    }
}

