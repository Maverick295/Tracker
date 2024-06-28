package com.tracker.tracker.errors;

public class EntityNotUpdatedException extends RuntimeException {

    public EntityNotUpdatedException(String message) {
        super(message);
    }

    public EntityNotUpdatedException() {}
}
