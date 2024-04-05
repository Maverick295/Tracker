package com.tracker.tracker.services.authentication;

import com.tracker.tracker.entities.Customer;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;

public interface AuthenticationService {
    List<GrantedAuthority> getGrantedAuthorities(Customer customer);
    Customer getAuthentication();
    void saveCustomerAndUpdateSession(Customer customer);
}
