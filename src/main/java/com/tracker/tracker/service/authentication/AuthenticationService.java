package com.tracker.tracker.service.authentication;

import com.tracker.tracker.entity.Customer;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;

public interface AuthenticationService {
    List<GrantedAuthority> getGrantedAuthorities(Customer customer);
    Customer getAuthentication();
}
