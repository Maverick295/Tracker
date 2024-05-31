package com.tracker.tracker.utils.validate;

import com.tracker.tracker.services.customer.CustomerService;
import io.micrometer.common.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Component;

@Component
public class AccountUtil {
    private final CustomerService customerService;

    @Autowired
    public AccountUtil(CustomerService customerService) {
        this.customerService = customerService;
    }

    public boolean validChangedUsername(String username) {
        if (StringUtils.isBlank(username)) {
            return false;
        }

        if (!customerService.getAuthenticatedCustomer().getUsername().equals(username)) {
            boolean usernameExists = customerService.findByUsername(username).isPresent();

            return !usernameExists;
        }

        return true;
    }

    public boolean validChangedEmail(String email) {
        if (StringUtils.isBlank(email)) {
            return false;
        }

        if (!customerService.getAuthenticatedCustomer().getEmail().equals(email)) {
            boolean emailExist = customerService.findByEmail(email).isPresent();

            return !emailExist;
        }

        return true;
    }
}
