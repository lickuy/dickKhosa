package com.enviro.assessment.grad001.dickkhosa.enviro365withdrawalmanager.exceptions;

/**
 * Exception representing an invalid date format error.
 * This exception is thrown when a date string does not match the expected format.
 *
 * @author Dick Khosa
 * @since August 28, 2023
 */
public class InvalidDateFormatException extends RuntimeException {

    /**
     * Constructs a new InvalidDateFormatException with the specified detail message.
     *
     * @param message The detail message.
     */
    public InvalidDateFormatException(String message) {
        super(message);
    }

    /**
     * Constructs a new InvalidDateFormatException with the specified detail message and cause.
     *
     * @param message The detail message.
     * @param cause   The cause of the exception.
     */
    public InvalidDateFormatException(String message, Throwable cause) {
        super(message, cause);
    }
}
