package com.tracker.tracker.utils.errors.company;

public class CompanyDBIntegrityConstraints extends RuntimeException {

    public CompanyDBIntegrityConstraints(String msg) {
        super(msg);
    }
}
