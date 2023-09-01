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
        return withdrawalNoticeRepository.findById(id);
    }
    /**
     * Calculates 90% of the withdrawal amount.
     *
     * @param withdrawalNoticeId The ID of the withdrawal notice for which to calculate 90%.
     * @return An optional containing the calculated amount if the withdrawal notice is found, or empty if not found.
     */
    @Override
    public Optional<Double> calculateNinetyPercent(Long withdrawalNoticeId) {
        Optional<WithdrawalNotice> withdrawalNoticeOptional = withdrawalNoticeRepository.findById(withdrawalNoticeId);

        if (withdrawalNoticeOptional.isPresent()) {
            WithdrawalNotice withdrawalNotice = withdrawalNoticeOptional.get();
            double amount = (double) withdrawalNotice.getAmount();
            double ninetyPercent = amount * 0.90;
            return Optional.of(ninetyPercent);
        } else {
            return Optional.empty(); // Withdrawal notice not found
        }
    }


    /**
     * Retrieves all withdrawal notices.
     *
     * @return The list of all withdrawal notices.
     */
    @Override
    public List<WithdrawalNotice> getAllWithdrawalNotices() {
        return withdrawalNoticeRepository.findAll();
    }
}
