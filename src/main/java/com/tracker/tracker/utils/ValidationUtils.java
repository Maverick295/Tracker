package com.tracker.tracker.utils;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.function.Function;

public class ValidationUtils {

    private ValidationUtils() {
        throw new AssertionError("Utility class should not be instantiated");
    }

    public static void checkErrors(BindingResult bindingResult, Function<String, RuntimeException> exceptionCreator) {
        if (bindingResult.hasErrors()) {
            StringBuilder errors = new StringBuilder();
            for (FieldError fieldError : bindingResult.getFieldErrors()) {
                errors.append(fieldError.getField())
                        .append(": ").append(fieldError.getDefaultMessage())
                        .append("; ");
            }
            throw exceptionCreator.apply(errors.toString());
        }
    }
}
