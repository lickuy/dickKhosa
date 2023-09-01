package com.enviro.assessment.grad001.dickkhosa.enviro365withdrawalmanager.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Custom exception class for handling resource not found errors (HTTP 404).
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundException extends RuntimeException {

    /**
     * Constructs a new NotFoundException with the specified error message.
     *
     * @param message A description of the error.
     */
    public NotFoundException(String message) {
        super(message);
    }

    /**
     * Constructs a new NotFoundException with the specified error message and a cause.
     *
     * @param message A description of the error.
     * @param cause   The cause of the error, typically another exception.
     */
    public NotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
