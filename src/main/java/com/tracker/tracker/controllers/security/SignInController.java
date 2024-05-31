package com.tracker.tracker.controllers.security;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SignInController {
    @GetMapping("/sign-in")
    public ModelAndView loginForm() {
        return new ModelAndView("security/sign-in");
    }
}
