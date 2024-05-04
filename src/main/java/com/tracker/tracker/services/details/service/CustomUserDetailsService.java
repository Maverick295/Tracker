package com.tracker.tracker.services.details.service;

import com.tracker.tracker.entities.Customer;
import com.tracker.tracker.entities.role.Role;
import com.tracker.tracker.services.customer.CustomerService;
import com.tracker.tracker.services.details.CustomUserDetails;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final CustomerService customerService;

    @Autowired
    public CustomUserDetailsService(CustomerService customerService) {
        this.customerService = customerService;
    }

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(@NotNull String username) throws UsernameNotFoundException {
        Customer customer = customerService.findByUsername(username);

        List<GrantedAuthority> role = getGrantedAuthorities(customer);

        return CustomUserDetails.build(
                customer,
                role
        );
    }

    public List<GrantedAuthority> getGrantedAuthorities(@NotNull Customer customer) {
        List<GrantedAuthority> role = new ArrayList<>();
        role.add(new SimpleGrantedAuthority(customer.isActive() ? customer.getRole() : Role.BANNED.getAuthority()));

        return role;
    }
}
