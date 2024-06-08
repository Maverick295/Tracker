package com.tracker.tracker.utils.validate;

import com.tracker.tracker.patterns.Patterns;
import io.micrometer.common.util.StringUtils;
import org.springframework.stereotype.Component;

@Component
public class PersonalUtil {

    public boolean validatePhone(String phone) {
        if (StringUtils.isBlank(phone)) {
            return false;
        }

        if (!Patterns.PHONE_PATTERN.matcher(phone).matches()) {
            return false;
        }

        return true;
    }
}
