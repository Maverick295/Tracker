package com.tracker.tracker.controller;

import com.tracker.tracker.entity.Customer;
import com.tracker.tracker.form.SignUpForm;
import com.tracker.tracker.service.customer.CustomerService;
import com.tracker.tracker.util.RedirectUtil;
import com.tracker.tracker.validator.SignUpFormValidator;
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
    public SignUpController(SignUpFormValidator signUpFormValidator, CustomerService customerService) {
        this.signUpFormValidator = signUpFormValidator;
        this.customerService = customerService;
    }

    @InitBinder("signUpForm")
    public void setSignUpForm(WebDataBinder binder) {
        binder.addValidators(signUpFormValidator);
    }

    @GetMapping
    public ModelAndView registrationGet() {
        return new ModelAndView("sign-up")
                .addObject("signUpForm", new SignUpForm());
    }

    @PostMapping
    public ModelAndView registrationPost(@ModelAttribute @Valid SignUpForm form, BindingResult result) {
        if (result.hasErrors()) {
            log.info("Возникли ошибки при отправке формы регистрации");
            return new ModelAndView("sign-up")
                    .addObject("signUpForm", new SignUpForm());
        }

        Customer customer = customerService.createCustomer(form);
        customerService.save(customer);
        log.info("Пользователь с ником " + customer.getUsername() + "успешно создан!");

        return RedirectUtil.redirect("/sign-in");
    }
}
