package com.tracker.tracker.utils.errors.user;

public class UserNotCreatedException extends RuntimeException {

    public UserNotCreatedException(String msg) {
        super(msg);
    }
}
