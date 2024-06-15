package com.tracker.tracker.utils.errors.user;

public class UserAccessViolationException extends RuntimeException {

    public UserAccessViolationException(String message) {
        super(message);
    }
}
