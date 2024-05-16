package com.tracker.tracker.controllers.main;

import com.tracker.tracker.entities.Customer;
import com.tracker.tracker.services.authentication.AuthenticationService;
import com.tracker.tracker.services.customer.CustomerService;
import com.tracker.tracker.services.models.ModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class MainController {
    private final CustomerService customerService;
    private final ModelService modelService;
    private final AuthenticationService authenticationService;

    @Autowired
    public MainController(
            CustomerService customerService,
            ModelService modelService,
            AuthenticationService authenticationService) {
        this.customerService = customerService;
        this.modelService = modelService;
        this.authenticationService = authenticationService;
    }

    @GetMapping
    public ModelAndView mainPage(
    ) {
        Customer authenticationCustomer = customerService.getAuthenticatedCustomer();

        return new ModelAndView("main/main-page")
                .addObject("authenticationCustomer", modelService.getMainPageModel(authenticationCustomer));
    }
}
