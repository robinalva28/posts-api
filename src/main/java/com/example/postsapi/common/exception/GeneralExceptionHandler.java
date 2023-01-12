package com.example.postsapi.common.exception;

import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.webjars.NotFoundException;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

@ControllerAdvice
public class GeneralExceptionHandler {

    @ExceptionHandler(value = {NotFoundException.class})
    public ResponseEntity<List<GenericError>> notFoundExceptionHandler(NotFoundException e) {

        var errors = List.of(new GenericError(null, e.getMessage(), LocalDateTime.now(ZoneId.of("Z"))));

        return new ResponseEntity<>(errors, HttpStatus.NOT_FOUND);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(value = {ConstraintViolationException.class})
    public ResponseEntity<List<GenericError>> constraintViolationExceptionHandler(ConstraintViolationException e) {

        var errors = List.of(new GenericError(null, e.getMessage(), LocalDateTime.now(ZoneId.of("Z"))));

        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

}
