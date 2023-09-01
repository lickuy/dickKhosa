package com.enviro.assessment.grad001.dickkhosa.enviro365withdrawalmanager.exceptions;

/**
 * Custom exception class for handling errors related to the generation of withdrawal statements.
 */
public class WithdrawalStatementGenerationException extends RuntimeException {

    /**
     * Constructs a new WithdrawalStatementGenerationException with the specified error message.
     *
     * @param message A description of the error.
     */
    public WithdrawalStatementGenerationException(String message) {
        super(message);
    }

    /**
     * Constructs a new WithdrawalStatementGenerationException with the specified error message and a cause.
     *
     * @param message A description of the error.
     * @param cause   The cause of the error, typically another exception.
     */
    public WithdrawalStatementGenerationException(String message, Throwable cause) {
        super(message, cause);
    }
}
