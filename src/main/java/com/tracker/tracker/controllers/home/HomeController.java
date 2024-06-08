package com.tracker.tracker.controllers.home;

import com.tracker.tracker.entities.Customer;
import com.tracker.tracker.services.authentication.AuthenticationService;
import com.tracker.tracker.services.customer.CustomerService;
import com.tracker.tracker.services.models.ModelService;
import com.tracker.tracker.utils.RedirectUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Objects;

@Controller
@RequestMapping("/")
public class HomeController {
    private final CustomerService customerService;
    private final ModelService modelService;
    private final AuthenticationService authenticationService;

    @Autowired
    public HomeController(
            CustomerService customerService,
            ModelService modelService,
            AuthenticationService authenticationService
    ) {
        this.customerService = customerService;
        this.modelService = modelService;
        this.authenticationService = authenticationService;
    }

    @GetMapping
    public ModelAndView index(Authentication authentication) {
        if (Objects.nonNull(authentication) && authentication.isAuthenticated()) {
            return RedirectUtil.redirect("/home");
        }

        return new ModelAndView("/home/index");
    }

    @GetMapping("/home")
    public ModelAndView home() {
        Customer authenticatedCustomer = customerService.getAuthenticatedCustomer();
        authenticationService.setUserAuthentication(authenticatedCustomer.getUsername());

        return new ModelAndView("/home/home")
                .addObject("authenticatedCustomer", modelService.getMainPageModel(authenticatedCustomer));
    }
}
