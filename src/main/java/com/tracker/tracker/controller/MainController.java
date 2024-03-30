package com.tracker.tracker.controller;

import com.tracker.tracker.form.SignUpForm;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class MainController {

    @GetMapping
    public ModelAndView mainPage() {
        return new ModelAndView("main-page");
    }
}
