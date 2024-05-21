package com.tracker.tracker.controllers.security;

import com.tracker.tracker.entities.Customer;
import com.tracker.tracker.forms.security.SignUpForm;
import com.tracker.tracker.services.customer.CustomerService;
import com.tracker.tracker.utils.RedirectUtil;
import com.tracker.tracker.validators.security.SignUpFormValidator;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/sign-up")
@Slf4j
public class SignUpController {
    private final SignUpFormValidator signUpFormValidator;
    private final CustomerService customerService;

    @Autowired
    public SignUpController(
            SignUpFormValidator signUpFormValidator,
            CustomerService customerService
    ) {
        this.signUpFormValidator = signUpFormValidator;
        this.customerService = customerService;
    }

    @InitBinder("signUpForm")
    public void setSignUpForm(WebDataBinder binder) {
        binder.addValidators(signUpFormValidator);
    }

    @GetMapping
    public ModelAndView registrationGet() {
        return new ModelAndView("security/sign-up")
                .addObject("signUpForm", new SignUpForm());
    }

    @PostMapping
    public ModelAndView registrationPost(
            @ModelAttribute @Valid SignUpForm form,
            BindingResult result
    ) {
        if (result.hasErrors()) {
            return new ModelAndView("security/sign-up")
                    .addObject("signUpForm", new SignUpForm())
                    .addObject("username", "");
        }

        Customer customer = customerService.createCustomer(form);
        customerService.save(customer);

        return RedirectUtil.redirect("/sign-in");
    }
}
