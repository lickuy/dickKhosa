package com.enviro.assessment.grad001.dickkhosa.enviro365withdrawalmanager.exceptions;

import lombok.Getter;

/**
 * Custom exception class for handling cases where an investor with a specific ID is not found.
 */
@Getter
public class InvestorNotFoundException extends RuntimeException {

    /**
     * The ID of the investor that was not found.
     */
    private final Long investorId;

    /**
     * Constructs a new InvestorNotFoundException with the given investor ID.
     *
     * @param investorId The ID of the investor that was not found.
     */
    public InvestorNotFoundException(String investorId) {
        super("Investor with ID " + investorId + " not found");
        this.investorId = Long.parseLong(investorId); // Convert String to Long
    }
}
