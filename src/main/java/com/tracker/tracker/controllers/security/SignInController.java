package com.tracker.tracker.controllers.security;

import com.tracker.tracker.services.customer.CustomerService;
import com.tracker.tracker.utils.RedirectUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SignInController {
    private CustomerService customerService;

    @Autowired
    public SignInController(CustomerService customerService) {
        this.customerService = customerService;
    }


    @GetMapping("/sign-in")
    public ModelAndView loginForm() {
        return new ModelAndView("security/sign-in");
    }

    @PostMapping("/sign-in")
    public ModelAndView login() {

        return RedirectUtil.redirect("/");
    }
}
