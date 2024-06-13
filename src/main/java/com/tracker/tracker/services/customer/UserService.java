package com.tracker.tracker.services.customer;

import com.tracker.tracker.entities.User;
import com.tracker.tracker.dto.user.RegistrationDTO;

import java.util.Optional;

public interface UserService {
    Optional<User> findByUsername(String username);

    Optional<User> findByEmail(String email);

    User getByUsername(String username);

    User getByEmail(String email);

    void save(User user);

    User create(RegistrationDTO dto);

    User getAuthenticatedUser();
}
