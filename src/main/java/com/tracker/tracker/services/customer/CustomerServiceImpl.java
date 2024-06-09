package com.tracker.tracker.services.customer;

import com.tracker.tracker.entities.Customer;
import com.tracker.tracker.entities.role.Role;
import com.tracker.tracker.forms.security.SignUpForm;
import com.tracker.tracker.repositories.CustomerRepository;
import com.tracker.tracker.security.CustomerDetails;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private final PasswordEncoder encoder;
    private final HttpSession session;

    @Autowired
    public CustomerServiceImpl(
            CustomerRepository customerRepository,
            PasswordEncoder encoder,
            HttpSession session
    ) {
        this.customerRepository = customerRepository;
        this.encoder = encoder;
        this.session = session;
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
                .setRoles(Role.USER.getAuthority());
    }

    @Override
    public Customer getAuthenticatedCustomer() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated() || authentication.getPrincipal().equals("anonymousUser")) {
            return null;
        }

        Object principal = authentication.getPrincipal();
        if (principal instanceof UserDetails) {
            return findByUsername(((UserDetails) principal).getUsername());
        }

        return null;
    }

    @Override
    @Transactional
    public void saveCustomerAndUpdateSession(Customer customer) {
        if (customer == null) {
            throw new IllegalArgumentException("'customer' == null");
        }

        try {
            save(customer);
            updateAuthentication(customer);
        } catch (Exception e) {
            throw new RuntimeException("Ошибка во время обновления сессии: ", e);
        }
    }

    private void updateAuthentication(Customer customer) {
        CustomerDetails customerDetails = new CustomerDetails(customer);
        UserDetails userDetails = new User(customerDetails.getUsername(), customerDetails.getPassword(), customerDetails.getAuthorities());
        Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        session.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, SecurityContextHolder.getContext());
    }
}

