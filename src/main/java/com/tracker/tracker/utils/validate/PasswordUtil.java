package com.tracker.tracker.utils.validate;

import com.tracker.tracker.services.customer.CustomerService;
import io.micrometer.common.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class PasswordUtil {
    private final PasswordEncoder encoder;
    private final CustomerService customerService;

    @Autowired
    public PasswordUtil(PasswordEncoder encoder, CustomerService customerService) {
        this.encoder = encoder;
        this.customerService = customerService;
    } 

    public boolean validateOldPassword(String oldPassword, String newPassword) {
        if (StringUtils.isBlank(oldPassword)) {
            return false;
        }

        if (!encoder.matches(oldPassword, customerService.getAuthenticatedCustomer().getPassword())) {
            return false;
        }

        if (encoder.matches(oldPassword, encoder.encode(newPassword))) {
            return false;
        }

        return true;
    }

    public boolean validateNewPassword(String newPassword) {
        if (StringUtils.isBlank(newPassword)) {
            return false;
        }

        if (newPassword.length() > 50) {
            return false;
        }

        return true;
    }

    public boolean validateConfirmPassword(String newPassword, String confirmPassword) {
        if (StringUtils.isBlank(confirmPassword)) {
            return false;
        }

        if (!encoder.matches(confirmPassword, encoder.encode(newPassword))) {
            return false;
        }

        return true;
    }
}
