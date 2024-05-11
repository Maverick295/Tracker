package com.tracker.tracker.controllers.profile;

import com.tracker.tracker.entities.Customer;
import com.tracker.tracker.services.customer.CustomerService;
import com.tracker.tracker.services.models.ModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
@RequestMapping("/profile")
public class ProfileController {
    private final CustomerService customerService;
    private final ModelService modelService;

    @Autowired
    public ProfileController(
            CustomerService customerService,
            ModelService modelService
    ) {
        this.customerService = customerService;
        this.modelService = modelService;
    }

    @GetMapping("/{username}")
    public ModelAndView profile(
            @PathVariable String username
    ) {
        Customer authenticationCustomer = customerService.getAuthenticatedCustomer();
        Optional<Customer> otherCustomer = customerService.findByUsername(username);

        if (authenticationCustomer != null && username.equals(authenticationCustomer.getUsername())) {
            return new ModelAndView("profile/my-profile")
                    .addObject("authenticationCustomer", modelService.getProfileModel(authenticationCustomer));
        } else {
            return otherCustomer.map(customer -> new ModelAndView("profile/other-profile")
                    .addObject("otherCustomer", modelService.getProfileModel(customer))).orElseGet(() -> new ModelAndView("errors/not-found"));
        }
    }
}
