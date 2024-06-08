package com.tracker.tracker.controllers.auth;

import com.tracker.tracker.entities.Customer;
import com.tracker.tracker.forms.security.SignUpForm;
import com.tracker.tracker.services.customer.CustomerService;
import com.tracker.tracker.utils.RedirectUtil;
import com.tracker.tracker.validators.security.SignUpFormValidator;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Objects;

@Controller
@RequestMapping("/registration")
public class RegistrationController {
    private final SignUpFormValidator signUpFormValidator;
    private final CustomerService customerService;

    @Autowired
    public RegistrationController(
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
    public ModelAndView registrationGet(Authentication authentication) {
        if (Objects.nonNull(authentication) && authentication.isAuthenticated()) {
            return RedirectUtil.redirect("/home");
        }

        return new ModelAndView("/security/registration")
                .addObject("signUpForm", new SignUpForm());
    }

    @PostMapping
    public ModelAndView registrationPost(
            @ModelAttribute @Valid SignUpForm form,
            BindingResult result
    ) {
        if (result.hasErrors()) {
            return new ModelAndView("/security/registration")
                    .addObject("signUpForm", new SignUpForm())
                    .addObject("username", "");
        }
        Customer customer = customerService.createCustomer(form);
        customerService.save(customer);

        return RedirectUtil.redirect("/login");
    }
}
