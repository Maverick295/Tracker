package com.tracker.tracker.patterns;

import java.util.regex.Pattern;

public class Patterns {
    public static final String PHONE = "^((8|\\+7)[\\- ]?)?(\\(?\\d{3}\\)?[\\- ]?)?[\\d\\- ]{7,10}$";
    public static Pattern PHONE_PATTERN = Pattern.compile(PHONE);
    public static String EMAIL = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
        + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
    public static Pattern EMAIL_PATTERN = Pattern.compile(EMAIL);
}
