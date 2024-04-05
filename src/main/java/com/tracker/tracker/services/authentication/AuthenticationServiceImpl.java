package com.tracker.tracker.services.authentication;

import com.tracker.tracker.entities.Customer;
import com.tracker.tracker.entities.role.Role;
import com.tracker.tracker.services.customer.CustomerService;
import com.tracker.tracker.services.principal.PrincipalServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
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

@Slf4j
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

    @Override
    public void saveCustomerAndUpdateSession(Customer customer) {
        if (customer == null) {
            throw new IllegalArgumentException("'customer' == null");
        }

        try {
            customerService.save(customer);
            UserDetails updatedUserDetails = loadUserByUsername(customer.getUsername());

            if (updatedUserDetails != null) {
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(updatedUserDetails, updatedUserDetails.getPassword(), updatedUserDetails.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authentication);
            } else {
                throw new IllegalArgumentException("Не удалось обновить контекст безопасности для пользователя: " + customer.getEmail());
            }
        } catch (Exception e) {
            throw new RuntimeException("Ошибка во время обновления сессии: ", e);
        }
    }
}
