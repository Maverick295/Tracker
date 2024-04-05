package com.tracker.tracker.controllers.profile;

import com.tracker.tracker.entities.Customer;
import com.tracker.tracker.services.authentication.AuthenticationService;
import com.tracker.tracker.services.customer.CustomerService;
import com.tracker.tracker.services.models.ModelService;
import org.springframework.beans.factory.annotation.Autowired;
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
    private final ModelService modelService;

    @Autowired
    public ProfileController(
            AuthenticationService authenticationService,
            CustomerService customerService,
            ModelService modelService
    ) {
        this.authenticationService = authenticationService;
        this.customerService = customerService;
        this.modelService = modelService;
    }

    @GetMapping("/{username}")
    public ModelAndView profile(@PathVariable String username) {
        Customer authenticationCustomer = authenticationService.getAuthentication();
        Customer otherCustomer = customerService.findByUsername(username);
        if (authenticationCustomer != null && username.equals(authenticationCustomer.getUsername())) {
            return new ModelAndView("profiletempl/my-profile")
                    .addObject("authenticationCustomer", modelService.getProfileModel(authenticationCustomer));
        } else if (otherCustomer != null) {
            return new ModelAndView("profiletempl/other-profile")
                    .addObject("otherCustomer", modelService.getProfileModel(otherCustomer));
        } else {
            return new ModelAndView("errorstempl/not-found");
        }
    }
}
