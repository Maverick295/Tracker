package com.tracker.tracker.controllers.main;

import com.tracker.tracker.entities.Customer;
import com.tracker.tracker.services.authentication.AuthenticationService;
import com.tracker.tracker.services.customer.CustomerService;
import com.tracker.tracker.services.models.ModelService;
import com.tracker.tracker.utils.RedirectUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
@Slf4j
public class HomeController {
    private final CustomerService customerService;
    private final ModelService modelService;
    private final AuthenticationService authenticationService;

    @Autowired
    public HomeController(
            CustomerService customerService,
            ModelService modelService,
            AuthenticationService authenticationService) {
        this.customerService = customerService;
        this.modelService = modelService;
        this.authenticationService = authenticationService;
    }

    @GetMapping
    public ModelAndView homePageBeforeLogin(Authentication authentication) {
        if (authentication != null && authentication.isAuthenticated()) {
            return RedirectUtil.redirect("/home");
        }

        return new ModelAndView("home/home-before");
    }

    @GetMapping("/home")
    public ModelAndView homePageAfterLogin() {
        Customer authenticatedCustomer = customerService.getAuthenticatedCustomer();

        authenticationService.setUserAuthentication(authenticatedCustomer.getUsername());

        return new ModelAndView("home/home-after")
                .addObject("authenticatedCustomer", modelService.getProfileModel(authenticatedCustomer));
    }
}
