package com.hikru.assessment.tools;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ControllerResponseTool {

    public ResponseEntity<?> ok(Object o) {
        return new ResponseEntity<>(o, HttpStatus.OK);
    }

    public ResponseEntity<?> badRequest(Object o) {
        return new ResponseEntity<>(o, HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<?> notFound(String message) {
        return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<?> serverError(String message) { return new ResponseEntity<>(message, HttpStatus.INTERNAL_SERVER_ERROR); }

}
