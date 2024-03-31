package com.tracker.tracker.service.authentication;

import com.tracker.tracker.entity.Customer;
import com.tracker.tracker.entity.role.Role;
import com.tracker.tracker.service.customer.CustomerService;
import com.tracker.tracker.service.principal.PrincipalServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AuthenticationServiceImpl implements AuthenticationService, UserDetailsService {
    private final CustomerService customerService;

    @Autowired
    public AuthenticationServiceImpl(CustomerService customerService) {
        this.customerService = customerService;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Customer customer = customerService.findByUsername(username);

        if (customer == null) {
            throw new UsernameNotFoundException(username);
        }

        List<GrantedAuthority> role = getGrantedAuthorities(customer);

        return new PrincipalServiceImpl(
                customer.getCustomerId(),
                customer.getUsername(),
                customer.getEmail(),
                customer.getPassword(),
                role,
                customer.isActive()
        );
    }


    @Override
    public List<GrantedAuthority> getGrantedAuthorities(Customer customer) {
        List<GrantedAuthority> role = new ArrayList<>();
        role.add(new SimpleGrantedAuthority(customer.isActive() ? customer.getRole() : Role.BANNED.getAuthority()));
        return role;
    }

    @Override
    public Customer getAuthentication() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return customerService.findByUsername(authentication.getName());
    }
}
