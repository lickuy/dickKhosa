package com.enviro.assessment.grad001.dickkhosa.enviro365withdrawalmanager.services.interfaces;

import com.enviro.assessment.grad001.dickkhosa.enviro365withdrawalmanager.entities.Investor;
import com.enviro.assessment.grad001.dickkhosa.enviro365withdrawalmanager.entities.WithdrawalNotice;

import java.util.List;
import java.util.Optional;

/**
 * Service interface for managing withdrawal notices.
 *
 * @author Dick Khosa
 * @since August 28, 2023
 */
public interface WithdrawalNoticeService {

    /**
     * Creates a new withdrawal notice.
     *
     * @param withdrawalNotice The withdrawal notice to create.
     * @return The created withdrawal notice.
     */
    WithdrawalNotice createWithdrawalNotice(WithdrawalNotice withdrawalNotice);

    /**
     * Retrieves the list of withdrawal notices for an investor.
     *
     * @param investor The investor for whom to retrieve the withdrawal notices.
     * @return The list of withdrawal notices.
     */
    List<WithdrawalNotice> getWithdrawalNoticesForInvestor(Investor investor);

    /**
     * Retrieves a withdrawal notice by its ID.
     *
     * @param id The ID of the withdrawal notice to retrieve.
     * @return An optional containing the withdrawal notice if found, or empty if not found.
     */
    Optional<WithdrawalNotice> getWithdrawalNoticeById(Long id);

    Optional<Double> calculateNinetyPercent(Long withdrawalNoticeId);

    Optional<Object> calculateNinetyPercentWithAgeStatement(Long withdrawalNoticeId);

    /**
     * Retrieves all withdrawal notices.
     *
     * @return The list of all withdrawal notices.
     */
    List<WithdrawalNotice> getAllWithdrawalNotices();
}
