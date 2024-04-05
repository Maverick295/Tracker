package com.tracker.tracker.controllers.security;

import com.tracker.tracker.utils.RedirectUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/sign-in")
public class SignInController {
    @GetMapping
    public ModelAndView login() {
        return new ModelAndView("securitytempl/sign-in");
    }

    @PostMapping
    public ModelAndView loginAction() {
        return RedirectUtil.redirect("/");
    }
}
