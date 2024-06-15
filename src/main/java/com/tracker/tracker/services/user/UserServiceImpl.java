package com.tracker.tracker.services.user;

import com.tracker.tracker.dto.user.RegistrationDTO;
import com.tracker.tracker.entities.User;
import com.tracker.tracker.entities.role.Role;
import com.tracker.tracker.repositories.UserRepository;
import com.tracker.tracker.utils.ServiceUtil;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder encoder;

    @Autowired
    public UserServiceImpl(
        UserRepository userRepository,
        PasswordEncoder encoder
    ) {
        this.userRepository = userRepository;
        this.encoder = encoder;
    }

    @Override
    public Optional<User> findByUsername(@NotNull String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public Optional<User> findByEmail(@NotNull String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User getByUsername(String username) {
        return findByUsername(username)
            .orElseThrow(
                () -> new EntityNotFoundException("User with username " + username + " not found")
            );
    }

    @Override
    public User getByEmail(String email) {
        return findByEmail(email)
            .orElseThrow(
                () -> new EntityNotFoundException("User with username " + email + " not found")
            );
    }

    @Override
    public void save(User user) {
        userRepository.save(user);
    }

    @Override
    public User create(RegistrationDTO dto) {
        return new User()
            .setUsername(dto.getUsername())
            .setEmail(dto.getEmail())
            .setPassword(encoder.encode(dto.getPassword()))
            .setActive(true)
            .setRole(Role.USER.getAuthority())
            .setUuid(ServiceUtil.generateUuid())
            .setDateOfCreate(LocalDate.now());
    }

    @Override
    public User getAuthenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        return findByUsername(authentication.getName())
            .orElseThrow(() -> new EntityNotFoundException("Авторизованного пользователя с таким именем не существует"));
    }
}

