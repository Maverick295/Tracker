package com.tracker.tracker.services.principal;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class PrincipalServiceImpl implements UserDetails, PrincipalService {
    private Long id;
    private String username;
    private String email;
    private String password;
    private List<GrantedAuthority> role;
    private boolean active;

    public PrincipalServiceImpl(
            Long id,
            String username,
            String email,
            String password,
            List<GrantedAuthority> role,
            boolean active
    ) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = role;
        this.active = active;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return role;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return active;
    }

    @Override
    public Long getId() {
        return id;
    }
}