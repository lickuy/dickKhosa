package com.enviro.assessment.grad001.dickkhosa.enviro365withdrawalmanager.exceptions;

/**
 * Exception representing an internal server error (HTTP 500).
 * This exception is thrown when an unexpected error occurs on the server.
 *
 * @author Dick Khosa
 * @since August 28, 2023
 */
public class InternalServerErrorException extends RuntimeException {

    /**
     * Constructs a new InternalServerErrorException with the specified detail message.
     *
     * @param message The detail message.
     */
    public InternalServerErrorException(String message) {
        super(message);
    }

    /**
     * Constructs a new InternalServerErrorException with the specified detail message and cause.
     *
     * @param message The detail message.
     * @param cause   The cause of the exception.
     */
    public InternalServerErrorException(String message, Throwable cause) {
        super(message, cause);
    }
}
