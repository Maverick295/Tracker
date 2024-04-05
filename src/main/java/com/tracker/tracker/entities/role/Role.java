package com.tracker.tracker.entities.role;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    USER, ADMIN, BANNED;

    @Override
    public String getAuthority() {
        return name();
    }
}
