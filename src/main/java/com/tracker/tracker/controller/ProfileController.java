package com.tracker.tracker.controller;

import com.tracker.tracker.entity.Customer;
import com.tracker.tracker.service.authentication.AuthenticationService;
import com.tracker.tracker.service.customer.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/profile")
public class ProfileController {
    private final AuthenticationService authenticationService;
    private final CustomerService customerService;

    @Autowired
    public ProfileController(AuthenticationService authenticationService, CustomerService customerService) {
        this.authenticationService = authenticationService;
        this.customerService = customerService;
    }

    @GetMapping("/{username}")
    public ModelAndView profile(@PathVariable String username) {
        Customer authenticationUser = authenticationService.getAuthentication();
        Customer otherUser = customerService.findByUsername(username);
        if (username.equals(authenticationUser.getUsername())) {
            return new ModelAndView("my-profile")
                    .addObject("authenticationUser", authenticationUser);
        } else {
            return new ModelAndView("other-profile")
                    .addObject("otherUser", otherUser);
        }
    }
}
