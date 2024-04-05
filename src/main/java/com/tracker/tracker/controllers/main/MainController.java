package com.tracker.tracker.controllers.main;

import com.tracker.tracker.entities.Customer;
import com.tracker.tracker.services.authentication.AuthenticationService;
import com.tracker.tracker.services.models.ModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class MainController {
    private final AuthenticationService authenticationService;
    private final ModelService modelService;

    @Autowired
    public MainController(
            AuthenticationService authenticationService,
            ModelService modelService
    ) {
        this.authenticationService = authenticationService;
        this.modelService = modelService;
    }

    @GetMapping
    public ModelAndView mainPage() {
        Customer authenticationCustomer = authenticationService.getAuthentication();
        return new ModelAndView("maintempl/main-page")
                .addObject("authenticationCustomer", modelService.getMainPageModel(authenticationCustomer));
    }
}
