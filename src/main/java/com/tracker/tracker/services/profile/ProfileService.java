package com.tracker.tracker.services.profile;

import com.tracker.tracker.entities.Customer;
import com.tracker.tracker.forms.profile.AccountInfoChangeForm;
import com.tracker.tracker.forms.profile.PasswordChangeForm;
import com.tracker.tracker.forms.profile.PersonalInfoChangeForm;

public interface ProfileService {
    Customer changePersonalInfo(PersonalInfoChangeForm form);
    Customer changeAccountInfo(AccountInfoChangeForm form);
    Customer changePasswordInfo(PasswordChangeForm form);
}
