package com.tracker.tracker.controllers.profile;

import com.tracker.tracker.entities.Customer;
import com.tracker.tracker.forms.profile.AccountInfoChangeForm;
import com.tracker.tracker.forms.profile.PasswordChangeForm;
import com.tracker.tracker.forms.profile.PersonalInfoChangeForm;
import com.tracker.tracker.models.profile.ProfileModel;
import com.tracker.tracker.services.customer.CustomerService;
import com.tracker.tracker.services.models.ModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/profile")
public class ProfileController {
    private final CustomerService customerService;
    private final ModelService modelService;

    @Autowired
    public ProfileController(
            CustomerService customerService,
            ModelService modelService
    ) {
        this.customerService = customerService;
        this.modelService = modelService;
    }

    @GetMapping("/{username}")
    public ModelAndView profile(
            @PathVariable String username,
            Authentication authentication
    ){
        Customer authenticatedCustomer = customerService.getAuthenticatedCustomer();
        ProfileModel authenticatedCustomerModel = modelService.getProfileModel(authenticatedCustomer);

        Customer otherCustomer = customerService.getCustomerByUsername(username);
        ProfileModel otherCustomerModel = modelService.getProfileModel(otherCustomer);

        if (!(authentication.isAuthenticated() && authenticatedCustomer.getName().equals(otherCustomer.getName()))) {
            return new ModelAndView("/profile/other-profile")
                    .addObject("otherCustomer", otherCustomerModel);
        }

        return new ModelAndView("/profile/auth-profile")
                .addObject("authenticatedCustomer", authenticatedCustomerModel);
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
}
