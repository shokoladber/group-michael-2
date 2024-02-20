package org.launchcode.caninecoach.exceptions;

import org.springframework.http.HttpStatus;

import lombok.Getter;

public class AppException extends RuntimeException {

    private final HttpStatus status;

    public AppException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }

    public HttpStatus getStatus() {
        return status;
    }
}

