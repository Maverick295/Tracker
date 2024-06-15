package com.tracker.tracker.controllers.handler;

import com.tracker.tracker.utils.ErrorResponseUtil;
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
    public ResponseEntity<ErrorResponseUtil> notFoundException(CompanyNotFoundException exception) {
        ErrorResponseUtil response = new ErrorResponseUtil(
                "Company with this Uuid not found",
                System.currentTimeMillis());

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CompanyDBIntegrityConstraints.class)
    public ResponseEntity<ErrorResponseUtil> notFoundException(CompanyDBIntegrityConstraints exception) {
        ErrorResponseUtil response = new ErrorResponseUtil(
                exception.getMessage(),
                System.currentTimeMillis());

        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(UserWithUsernameNotFoundException.class)
    public ResponseEntity<ErrorResponseUtil> notFoundException(UserWithUsernameNotFoundException exception) {
        ErrorResponseUtil response = new ErrorResponseUtil(
                "User with this username not found",
                System.currentTimeMillis());

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserWithEmailNotFoundException.class)
    public ResponseEntity<ErrorResponseUtil> notFoundException(UserWithEmailNotFoundException exception) {
        ErrorResponseUtil response = new ErrorResponseUtil(
                "User with this email not found",
                System.currentTimeMillis());

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserNotCreatedException.class)
    public ResponseEntity<ErrorResponseUtil> notFoundException(UserNotCreatedException exception) {
        ErrorResponseUtil response = new ErrorResponseUtil(
                exception.getMessage(),
                System.currentTimeMillis());

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserAccessViolationException.class)
    public ResponseEntity<ErrorResponseUtil> notFoundException(UserAccessViolationException exception) {
        ErrorResponseUtil response = new ErrorResponseUtil(
                exception.getMessage(),
                System.currentTimeMillis());

        return new ResponseEntity<>(response, HttpStatus.FORBIDDEN);
    }
}
