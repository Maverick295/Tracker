package com.tracker.tracker.services.authentication;

import com.tracker.tracker.entities.Customer;
import com.tracker.tracker.services.customer.CustomerService;
import com.tracker.tracker.services.details.service.CustomUserDetailsService;
import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    private final CustomUserDetailsService customUserDetailsService;
    private final CustomerService customerService;

    @Autowired
    public AuthenticationServiceImpl(
            CustomUserDetailsService customUserDetailsService,
            AuthenticationManager authenticationManager,
            CustomerService customerService
    ) {
        this.customUserDetailsService = customUserDetailsService;
        this.customerService = customerService;
    }

    @Override
    public void setUserAuthentication(@NotNull String username) {
        UserDetails userDetails = customUserDetailsService.loadUserByUsername(username);
        setAuthentication(
                new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        userDetails.getAuthorities()
                )
        );
    }

    @Override
    public void setAuthentication(@NotNull Authentication authentication) {
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    @Override
    public void updateSessionAfterChangeInfo(Customer customer) {
        customerService.save(customer);

        setUserAuthentication(customer.getUsername());
    }
}
