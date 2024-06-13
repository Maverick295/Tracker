package com.tracker.tracker.controllers.handler;

import com.tracker.tracker.utils.ErrorResponse;
import com.tracker.tracker.utils.errors.company.CompanyDBIntegrityConstraints;
import com.tracker.tracker.utils.errors.company.CompanyNotFoundException;
import com.tracker.tracker.utils.errors.user.UserAccessViolationException;
import com.tracker.tracker.utils.errors.user.UserNotCreatedException;
import com.tracker.tracker.utils.errors.user.UserWithEmailNotFoundException;
import com.tracker.tracker.utils.errors.user.UserWithUsernameNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionApiHandler {

    @ExceptionHandler(CompanyNotFoundException.class)
    public ResponseEntity<ErrorResponse> notFoundException(CompanyNotFoundException exception) {
        ErrorResponse response = new ErrorResponse(
                "Company with this Uuid not found",
                System.currentTimeMillis());

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CompanyDBIntegrityConstraints.class)
    public ResponseEntity<ErrorResponse> notFoundException(CompanyDBIntegrityConstraints exception) {
        ErrorResponse response = new ErrorResponse(
                exception.getMessage(),
                System.currentTimeMillis());

        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(UserWithUsernameNotFoundException.class)
    public ResponseEntity<ErrorResponse> notFoundException(UserWithUsernameNotFoundException exception) {
        ErrorResponse response = new ErrorResponse(
                "User with this username not found",
                System.currentTimeMillis());

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserWithEmailNotFoundException.class)
    public ResponseEntity<ErrorResponse> notFoundException(UserWithEmailNotFoundException exception) {
        ErrorResponse response = new ErrorResponse(
                "User with this email not found",
                System.currentTimeMillis());

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserNotCreatedException.class)
    public ResponseEntity<ErrorResponse> notFoundException(UserNotCreatedException exception) {
        ErrorResponse response = new ErrorResponse(
                exception.getMessage(),
                System.currentTimeMillis());

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserAccessViolationException.class)
    public ResponseEntity<ErrorResponse> notFoundException(UserAccessViolationException exception) {
        ErrorResponse response = new ErrorResponse(
                exception.getMessage(),
                System.currentTimeMillis());

        return new ResponseEntity<>(response, HttpStatus.FORBIDDEN);
    }
}
