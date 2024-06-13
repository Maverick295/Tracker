package com.tracker.tracker.controllers.auth;

import com.tracker.tracker.dto.user.RegistrationDTO;
import com.tracker.tracker.entities.User;
import com.tracker.tracker.services.customer.UserService;
import com.tracker.tracker.utils.errors.user.UserNotCreatedException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/registration")
public class RegistrationController {
    private final UserService userService;

    @Autowired
    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<HttpStatus> registrationPost(@RequestBody @Valid RegistrationDTO dto, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            StringBuilder errors = new StringBuilder();

            List<FieldError> fieldErrors = bindingResult.getFieldErrors();
            for (FieldError fieldError : fieldErrors) {
                errors.append(fieldError.getField())
                        .append(" : ").append(fieldError.getDefaultMessage())
                        .append(";");
            }

            throw new UserNotCreatedException(errors.toString());
        }

        User user = userService.create(dto);
        userService.save(user);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
