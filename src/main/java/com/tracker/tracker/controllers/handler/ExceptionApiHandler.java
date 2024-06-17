package com.tracker.tracker.controllers.handler;

import com.tracker.tracker.errors.DBIntegrityConstraintsException;
import com.tracker.tracker.errors.EntityNotCreatedException;
import com.tracker.tracker.errors.AccessDeniedException;
import com.tracker.tracker.utils.ErrorResponseUtil;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionApiHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorResponseUtil> entityNotFoundException(EntityNotFoundException exception) {
        ErrorResponseUtil response = new ErrorResponseUtil(
            exception.getMessage(),
            System.currentTimeMillis());

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DBIntegrityConstraintsException.class)
    public ResponseEntity<ErrorResponseUtil> dbIntegrityException(DBIntegrityConstraintsException exception) {
        ErrorResponseUtil response = new ErrorResponseUtil(
            exception.getMessage(),
            System.currentTimeMillis());

        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(EntityNotCreatedException.class)
    public ResponseEntity<ErrorResponseUtil> notCreatedException(EntityNotCreatedException exception) {
        ErrorResponseUtil response = new ErrorResponseUtil(
            exception.getMessage(),
            System.currentTimeMillis());

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ErrorResponseUtil> notFoundException(AccessDeniedException exception) {
        ErrorResponseUtil response = new ErrorResponseUtil(
            exception.getMessage(),
            System.currentTimeMillis());

        return new ResponseEntity<>(response, HttpStatus.FORBIDDEN);
    }
}
