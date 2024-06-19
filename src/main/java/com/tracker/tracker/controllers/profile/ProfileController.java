package com.tracker.tracker.controllers.profile;

import com.tracker.tracker.dto.user.UserDTO;
import com.tracker.tracker.entities.User;
import com.tracker.tracker.errors.AccessDeniedException;
import com.tracker.tracker.mappers.Mapper;
import com.tracker.tracker.services.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController("/profile")
public class ProfileController {
    private final UserService userService;
    private final Mapper<UserDTO, User> userMapper;

    @Autowired
    public ProfileController(
        UserService userService,
        Mapper<UserDTO, User> userMapper
    ) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @GetMapping("/{username}")
    public UserDTO profile(@PathVariable String username) {
        User authenticatedUser = userService.getAuthenticatedUser();
        User urlUser = userService.getByUsername(username);

        if (!urlUser.equals(authenticatedUser)) {
            throw new AccessDeniedException("You do not have permission to access this resource");
        }

        return userMapper.mapFrom(urlUser);
    }
}
