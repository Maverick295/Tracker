package com.tracker.tracker.details;

import com.tracker.tracker.entities.User;
import com.tracker.tracker.entities.role.Role;
import com.tracker.tracker.services.user.UserService;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final UserService userService;

    @Autowired
    public CustomUserDetailsService(UserService userService) {
        this.userService = userService;
    }

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(@NotNull String username) throws UsernameNotFoundException {
        User user = userService.getByUsername(username);

        List<GrantedAuthority> role = getGrantedAuthorities(user);

        return CustomUserDetails.build(
            user,
            role
        );
    }

    private List<GrantedAuthority> getGrantedAuthorities(@NotNull User user) {
        List<GrantedAuthority> role = new ArrayList<>();
        role.add(
            new SimpleGrantedAuthority(user.isActive() ? user.getRole() : Role.BANNED.getAuthority())
        );

        return role;
    }
}