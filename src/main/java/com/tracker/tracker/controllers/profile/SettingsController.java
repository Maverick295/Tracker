package com.tracker.tracker.controllers.profile;

import com.tracker.tracker.entities.Customer;
import com.tracker.tracker.forms.profile.AccountInfoChangeForm;
import com.tracker.tracker.forms.profile.PasswordChangeForm;
import com.tracker.tracker.forms.profile.PersonalInfoChangeForm;
import com.tracker.tracker.services.authentication.AuthenticationService;
import com.tracker.tracker.services.customer.CustomerService;
import com.tracker.tracker.services.models.ModelService;
import com.tracker.tracker.services.profile.ProfileService;
import com.tracker.tracker.utils.RedirectUtil;
import com.tracker.tracker.validators.profile.AccountInfoChangeFormValidator;
import com.tracker.tracker.validators.profile.PasswordChangeFormValidator;
import com.tracker.tracker.validators.profile.PersonalInfoChangeFormValidator;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/settings")
public class SettingsController {
    private final ModelService modelService;
    private final ProfileService profileService;
    private final CustomerService customerService;
    private final AccountInfoChangeFormValidator accountInfoChangeFormValidator;
    private final PasswordChangeFormValidator passwordChangeFormValidator;
    private final PersonalInfoChangeFormValidator personalInfoChangeFormValidator;
    private final AuthenticationService authenticationService;

    @Autowired
    public SettingsController(
            ModelService modelService,
            ProfileService profileService,
            CustomerService customerService,
            AccountInfoChangeFormValidator accountInfoChangeFormValidator,
            PasswordChangeFormValidator passwordChangeFormValidator,
            PersonalInfoChangeFormValidator personalInfoChangeFormValidator,
            AuthenticationService authenticationService) {
        this.modelService = modelService;
        this.profileService = profileService;
        this.customerService = customerService;
        this.accountInfoChangeFormValidator = accountInfoChangeFormValidator;
        this.passwordChangeFormValidator = passwordChangeFormValidator;
        this.personalInfoChangeFormValidator = personalInfoChangeFormValidator;
        this.authenticationService = authenticationService;
    }

    @InitBinder("accountInfoChangeForm")
    public void setAccountInfoChangeForm(WebDataBinder binder) {
        binder.addValidators(accountInfoChangeFormValidator);
    }

    @InitBinder("passwordChangeForm")
    public void setPasswordChangeForm(WebDataBinder binder) {
        binder.addValidators(passwordChangeFormValidator);
    }

    @InitBinder("signUpForm")
    public void setPersonalInfoChangeForm(WebDataBinder binder) {
        binder.addValidators(personalInfoChangeFormValidator);
    }

    @GetMapping("/account")
    public ModelAndView accountSetting() {
        Customer authenticationCustomer = customerService.getAuthenticatedCustomer();

        return new ModelAndView("profile/settings/account")
                .addObject("accountInfoChangeForm", new AccountInfoChangeForm())
                .addObject("authenticationCustomer", modelService.getProfileModel(authenticationCustomer));
    }

    @GetMapping("/password")
    public ModelAndView passwordSetting() {
        return new ModelAndView("profile/settings/password")
                .addObject("passwordChangeForm", new PasswordChangeForm());
    }

    @GetMapping("/personal")
    public ModelAndView personalSetting() {
        Customer authenticationCustomer = customerService.getAuthenticatedCustomer();

        return new ModelAndView("profile/settings/personal")
                .addObject("personalInfoChangeForm", new PersonalInfoChangeForm())
                .addObject("authenticationCustomer", modelService.getProfileModel(authenticationCustomer));
    }

    @PostMapping("/account")
    public ModelAndView accountChangeInfo(
            @ModelAttribute @Valid AccountInfoChangeForm form,
            BindingResult result
    ) {
        if (result.hasErrors()) {
            Customer authenticationCustomer = customerService.getAuthenticatedCustomer();

            return new ModelAndView("profile/settings/account")
                    .addObject("accountInfoChangeForm", new AccountInfoChangeForm())
                    .addObject("authenticationCustomer", modelService.getProfileModel(authenticationCustomer));
        }

        Customer authenticationCustomer = profileService.changeAccountInfo(form);
        authenticationService.updateSessionAfterChangeInfo(authenticationCustomer);

        return RedirectUtil.redirect("/settings/account");
    }

    @PostMapping("/password")
    public ModelAndView passwordChange(
            @ModelAttribute @Valid PasswordChangeForm form,
            BindingResult result
    ) {
        if (result.hasErrors()) {
            return new ModelAndView("profile/settings/password")
                    .addObject("passwordChangeForm", new PasswordChangeForm());
        }

        Customer authenticationCustomer = profileService.changePasswordInfo(form);
        customerService.save(authenticationCustomer);

        return RedirectUtil.redirect("/settings/password");
    }

    @PostMapping("/personal")
    public ModelAndView personalChangeInfo(
            @ModelAttribute @Valid PersonalInfoChangeForm form,
            BindingResult result
    ) {
        if (result.hasErrors()) {
            Customer authenticationCustomer = customerService.getAuthenticatedCustomer();

            return new ModelAndView("profile/settings/personal")
                    .addObject("personalInfoChangeForm", new PersonalInfoChangeForm())
                    .addObject("authenticationCustomer", modelService.getProfileModel(authenticationCustomer));
        }

        Customer authenticationCustomer = profileService.changePersonalInfo(form);
        customerService.save(authenticationCustomer);

        return RedirectUtil.redirect("/settings/personal");
    }
}
