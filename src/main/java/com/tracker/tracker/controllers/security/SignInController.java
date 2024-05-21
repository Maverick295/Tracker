package com.tracker.tracker.controllers.security;

import com.tracker.tracker.services.authentication.AuthenticationService;
import com.tracker.tracker.services.customer.CustomerService;
import com.tracker.tracker.utils.RedirectUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SignInController {
    private AuthenticationService authenticationService;
    private CustomerService customerService;

    @Autowired
    public SignInController(AuthenticationService authenticationService, CustomerService customerService) {
        this.authenticationService = authenticationService;
        this.customerService = customerService;
    }

    @GetMapping("/sign-in")
    public ModelAndView loginForm() {
        return new ModelAndView("security/sign-in");
    }
}
