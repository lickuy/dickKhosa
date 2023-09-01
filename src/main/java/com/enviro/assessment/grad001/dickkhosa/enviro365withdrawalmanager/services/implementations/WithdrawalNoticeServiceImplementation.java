package com.enviro.assessment.grad001.dickkhosa.enviro365withdrawalmanager.services.implementations;

import com.enviro.assessment.grad001.dickkhosa.enviro365withdrawalmanager.entities.Investor;
import com.enviro.assessment.grad001.dickkhosa.enviro365withdrawalmanager.entities.WithdrawalNotice;
import com.enviro.assessment.grad001.dickkhosa.enviro365withdrawalmanager.repositories.WithdrawalNoticeRepository;
import com.enviro.assessment.grad001.dickkhosa.enviro365withdrawalmanager.services.interfaces.WithdrawalNoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service implementation for managing withdrawal notices.
 *
 * @author Dick Khosa
 * @since August 28, 2023
 */
@Service
public class WithdrawalNoticeServiceImplementation implements WithdrawalNoticeService {

    private final WithdrawalNoticeRepository withdrawalNoticeRepository;

    @Autowired
    public WithdrawalNoticeServiceImplementation(WithdrawalNoticeRepository withdrawalNoticeRepository) {
        this.withdrawalNoticeRepository = withdrawalNoticeRepository;
    }

    /**
     * Creates a new withdrawal notice.
     *
     * @param withdrawalNotice The withdrawal notice to create.
     * @return The created withdrawal notice.
     */
    @Override
    public WithdrawalNotice createWithdrawalNotice(WithdrawalNotice withdrawalNotice) {
        // Implement creation logic and save to the repository
        return withdrawalNoticeRepository.save(withdrawalNotice);
    }

    /**
     * Retrieves all withdrawal notices for an investor.
     *
     * @param investor The investor for whom to retrieve withdrawal notices.
     * @return The list of withdrawal notices for the investor.
     */
    @Override
    public List<WithdrawalNotice> getWithdrawalNoticesForInvestor(Investor investor) {
        // Implement logic to fetch withdrawal notices for the given investor from the repository
        return withdrawalNoticeRepository.findByInvestor(investor);
    }

    /**
     * Retrieves a withdrawal notice by its ID.
     *
     * @param id The ID of the withdrawal notice to retrieve.
     * @return An optional containing the withdrawal notice if found, or empty if not found.
     */
    @Override
    public Optional<WithdrawalNotice> getWithdrawalNoticeById(Long id) {
        // Implement logic to fetch a withdrawal notice by its ID from the repository
        return withdrawalNoticeRepository.findById(id);
    }

    /**
     * Retrieves all withdrawal notices.
     *
     * @return The list of all withdrawal notices.
     */
    @Override
    public List<WithdrawalNotice> getAllWithdrawalNotices() {
        // Implement logic to fetch all withdrawal notices from the repository
        return withdrawalNoticeRepository.findAll();
    }
}
