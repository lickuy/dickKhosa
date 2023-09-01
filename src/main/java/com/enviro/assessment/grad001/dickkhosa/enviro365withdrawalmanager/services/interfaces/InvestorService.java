package com.enviro.assessment.grad001.dickkhosa.enviro365withdrawalmanager.services.interfaces;

import com.enviro.assessment.grad001.dickkhosa.enviro365withdrawalmanager.entities.Investor;

import java.util.Optional;

/**
 * Service interface for managing investors.
 *
 * @author Dick Khosa
 * @since August 28, 2023
 */
public interface InvestorService {

    /**
     * Creates a new investor.
     *
     * @param investor The investor to create.
     * @return The created investor.
     */
    Investor createInvestor(Investor investor);

    /**
     * Retrieves an investor by their ID.
     *
     * @param id The ID of the investor to retrieve.
     * @return An optional containing the investor if found, or empty if not found.
     */
    Optional<Investor> getInvestorById(Long id);

    /**
     * Updates an investor's information by their ID.
     *
     * @param id              The ID of the investor to update.
     * @param updatedInvestor The updated investor information.
     * @return The updated investor.
     */
    Investor updateInvestor(Long id, Investor updatedInvestor);

    /**
     * Archives an investor by their ID.
     *
     * @param id The ID of the investor to archive.
     * @return The archived investor.
     */
    Investor archiveInvestor(Long id);

    /**
     * Calculates the total balance of all products for an investor.
     *
     * @param investor The investor for whom to calculate the balance.
     * @return The total balance.
     */
    Double calculateProductsBalance(Investor investor);
}
