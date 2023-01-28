package com.example.libraryjpa.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class TechnicalException extends RuntimeException {
    private HttpStatus status;

    public TechnicalException(String message, Throwable cause, HttpStatus
            status) {
        super(message, cause);
        this.status = status != null ? status :
                HttpStatus.INTERNAL_SERVER_ERROR;
    }
}
