package com.hikru.assessment.exceptions;

import com.hikru.assessment.tools.ControllerResponseTool;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler extends ControllerResponseTool {

    /*@ExceptionHandler({RuntimeException.class})
    public ResponseEntity<?> handle500Exception(Exception exception) {
        return serverError(exception.getMessage());
    }*/

    @ExceptionHandler({PositionNotFoundException.class,
            DepartmentNotFoundException.class,
            RecruiterNotFoundException.class})
    public ResponseEntity<?> handle404Exception(Exception exception) {
        return notFound(exception.getMessage());
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<?> handleValidationErrors(MethodArgumentNotValidException exception) {
        return badRequest(exception.getBindingResult().getFieldErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toList()));
    }

    @ExceptionHandler({ConstraintViolationException.class})
    public ResponseEntity<?> handleConstraintViolationException(ConstraintViolationException exception) {
        return badRequest(exception.getConstraintViolations()
                .stream()
                .map(ConstraintViolation::getMessage)
                .collect(Collectors.toList()));
    }

}
