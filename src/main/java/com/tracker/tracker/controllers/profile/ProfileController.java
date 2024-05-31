package com.tracker.tracker.controllers.profile;

import com.tracker.tracker.entities.Customer;
import com.tracker.tracker.models.profile.ProfileModel;
import com.tracker.tracker.services.customer.CustomerService;
import com.tracker.tracker.services.models.ModelService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Objects;
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
            @PathVariable String username,
            Authentication authentication
    ){
        Customer authenticatedCustomer = customerService.getAuthenticatedCustomer();
        ProfileModel authenticatedCustomerModel = modelService.getProfileModel(authenticatedCustomer);

        Customer otherCustomer = customerService.getCustomerByUsername(username);
        ProfileModel otherCustomerModel = modelService.getProfileModel(otherCustomer);

        if (!(authentication.isAuthenticated() && authenticatedCustomer.getName().equals(otherCustomer.getName()))) {
            return new ModelAndView("profile/other-profile")
                    .addObject("otherUser", otherCustomerModel);
        }

        return new ModelAndView("profile/auth-profile")
                .addObject("authenticatedUser", authenticatedCustomerModel);
    }
}
