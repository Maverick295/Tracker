package com.tracker.tracker.controllers.auth;

import com.tracker.tracker.dto.user.RegistrationDTO;
import com.tracker.tracker.entities.User;
import com.tracker.tracker.services.customer.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/registration")
public class RegistrationController {
    private final UserService userService;

    @Autowired
    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<HttpStatus> registrationPost(@RequestBody RegistrationDTO dto) {
        User user = userService.create(dto);
        userService.save(user);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
