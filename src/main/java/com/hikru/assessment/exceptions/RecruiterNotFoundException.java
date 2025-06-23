package com.hikru.assessment.exceptions;

public class RecruiterNotFoundException extends RuntimeException {

    private final Integer id;

    public RecruiterNotFoundException(String message, Integer id) {
        super(message);
        this.id = id;
    }

    public String getMessage() {
        return super.getMessage() + " for id [" + this.id + "]";
    }

}
