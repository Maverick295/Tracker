package com.tracker.tracker.errors;

public class EntityNotCreatedException extends RuntimeException {
    public EntityNotCreatedException() {
    }

    public EntityNotCreatedException(String message) {
        super(message);
    }
}
