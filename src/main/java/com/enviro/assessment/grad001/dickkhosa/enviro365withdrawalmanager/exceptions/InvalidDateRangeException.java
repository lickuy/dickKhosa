package com.enviro.assessment.grad001.dickkhosa.enviro365withdrawalmanager.exceptions;

/**
 * Exception representing an invalid date range error.
 * This exception is thrown when a date range is not valid or falls outside acceptable boundaries.
 *
 * @author Dick Khosa
 * @since August 28, 2023
 */
public class InvalidDateRangeException extends RuntimeException {

    /**
     * Constructs a new InvalidDateRangeException with the specified detail message.
     *
     * @param message The detail message.
     */
    public InvalidDateRangeException(String message) {
        super(message);
    }

    /**
     * Constructs a new InvalidDateRangeException with the specified detail message and cause.
     *
     * @param message The detail message.
     * @param cause   The cause of the exception.
     */
    public InvalidDateRangeException(String message, Throwable cause) {
        super(message, cause);
    }
}
