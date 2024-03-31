package com.tracker.tracker.controller;

import com.tracker.tracker.service.authentication.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class MainController {
    private final AuthenticationService authenticationService;

    @Autowired
    public MainController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @GetMapping
    public ModelAndView mainPage() {
        return new ModelAndView("main-page")
                .addObject("authenticationUser", authenticationService.getAuthentication());
    }
}
