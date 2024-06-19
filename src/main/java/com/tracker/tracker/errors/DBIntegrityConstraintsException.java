package com.tracker.tracker.errors;

public class DBIntegrityConstraintsException extends RuntimeException {
    public DBIntegrityConstraintsException() {
    }

    public DBIntegrityConstraintsException(String message) {
        super(message);
    }
}
