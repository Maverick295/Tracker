package com.tracker.tracker.service.authentication;

import com.tracker.tracker.entity.Customer;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;
import java.util.Optional;

public interface AuthenticationService {
    List<GrantedAuthority> getGrantedAuthorities(Customer customer);
}
