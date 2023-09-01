package com.enviro.assessment.grad001.dickkhosa.enviro365withdrawalmanager.exceptions;

/**
 * Custom exception class for handling withdrawal validation errors.
 */
public class WithdrawalValidationException extends RuntimeException {

    /**
     * Constructs a new WithdrawalValidationException with the specified error message.
     *
     * @param message The error message explaining the reason for the validation failure.
     */
    public WithdrawalValidationException(String message) {
        super(message);
    }

    /**
     * Constructs a new WithdrawalValidationException with the specified error message and a cause.
     *
     * @param message The error message explaining the reason for the validation failure.
     * @param cause   The cause of the exception.
     */
    public WithdrawalValidationException(String message, Throwable cause) {
        super(message, cause);
    }
}
