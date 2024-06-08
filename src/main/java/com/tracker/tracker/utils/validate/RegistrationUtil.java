package com.tracker.tracker.utils.validate;

import com.tracker.tracker.patterns.Patterns;
import com.tracker.tracker.services.customer.CustomerService;
import io.micrometer.common.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RegistrationUtil {
    private final CustomerService customerService;

    @Autowired
    public RegistrationUtil(CustomerService customerService) {
        this.customerService = customerService;
    }

    public boolean validateUsername(String username) {
        if (StringUtils.isBlank(username)) {
            return false;
        }

        if (customerService.findByUsername(username).isPresent()) {
            return false;
        }

        if (username.length() < 3 || username.length() > 20) {
            return false;
        }

        return true;
    }

    public boolean validateEmail(String email) {
        if (StringUtils.isBlank(email)) {
            return false;
        }

        if (customerService.findByEmail(email).isPresent()) {
            return false;
        }

        if (!Patterns.EMAIL_PATTERN.matcher(email).matches()) {
            return false;
        }

        return true;
    }
}
