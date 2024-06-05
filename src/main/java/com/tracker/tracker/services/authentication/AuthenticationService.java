package com.tracker.tracker.services.authentication;

import com.tracker.tracker.entities.Customer;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.core.Authentication;

public interface AuthenticationService {
    void setUserAuthentication(String username);
    void setAuthentication(Authentication authentication);
}
