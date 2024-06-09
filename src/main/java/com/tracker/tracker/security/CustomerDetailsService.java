package com.tracker.tracker.security;

import com.tracker.tracker.entities.Customer;
import com.tracker.tracker.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class CustomerDetailsService implements UserDetailsService {

    private final CustomerRepository customerRepository;

    public CustomerDetailsService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }


    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Customer> customer = Optional.ofNullable(customerRepository.findByUsername(username));

        if (customer.isEmpty()) {
            throw new UsernameNotFoundException("User not found");
        }

        return new CustomerDetails(customer.get());
    }


}
