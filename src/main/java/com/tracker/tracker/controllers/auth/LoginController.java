package com.tracker.tracker.controllers.auth;

import com.tracker.tracker.utils.RedirectUtil;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Objects;

@Controller
@RequestMapping("/login")
public class LoginController {
    @GetMapping
    public ModelAndView loginForm(Authentication authentication) {
        if (Objects.nonNull(authentication) && authentication.isAuthenticated()) {
            return RedirectUtil.redirect("/home");
        }

        return new ModelAndView("/security/login");
    }
}
