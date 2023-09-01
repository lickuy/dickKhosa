package com.enviro.assessment.grad001.dickkhosa.enviro365withdrawalmanager.services.implementations;

import com.enviro.assessment.grad001.dickkhosa.enviro365withdrawalmanager.entities.Investor;
import com.enviro.assessment.grad001.dickkhosa.enviro365withdrawalmanager.entities.WithdrawalNotice;
import com.enviro.assessment.grad001.dickkhosa.enviro365withdrawalmanager.repositories.WithdrawalNoticeRepository;
import com.enviro.assessment.grad001.dickkhosa.enviro365withdrawalmanager.services.interfaces.InvestorService;
import com.enviro.assessment.grad001.dickkhosa.enviro365withdrawalmanager.services.interfaces.WithdrawalNoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
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
    private final InvestorService investorService; // Inject InvestorService

    @Autowired
    public WithdrawalNoticeServiceImplementation(
            WithdrawalNoticeRepository withdrawalNoticeRepository,
            InvestorService investorService) {
        this.withdrawalNoticeRepository = withdrawalNoticeRepository;
        this.investorService = investorService; // Initialize InvestorService
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

    @Override
    public Optional<Double> calculateNinetyPercent(Long withdrawalNoticeId) {
        return Optional.empty();
    }

    /**
     * Calculates 90% of the withdrawal amount and generates an age statement.
     *
     * @param withdrawalNoticeId The ID of the withdrawal notice for which to calculate 90%.
     * @return An optional containing the calculated amount and age statement if the withdrawal notice is found, or empty if not found.
     */
    @Override
    public Optional<Object> calculateNinetyPercentWithAgeStatement(Long withdrawalNoticeId) {
        Optional<WithdrawalNotice> withdrawalNoticeOptional = withdrawalNoticeRepository.findById(withdrawalNoticeId);

        if (withdrawalNoticeOptional.isPresent()) {
            WithdrawalNotice withdrawalNotice = withdrawalNoticeOptional.get();
            double amount = (double) withdrawalNotice.getAmount(); // Cast to double not needed

            // Retrieve the investor to calculate age
            Optional<Investor> investorOptional = investorService.getInvestorById(Long.valueOf(withdrawalNotice.getInvestor().getId()));

            if (investorOptional.isPresent()) {
                Investor investor = investorOptional.get();

                // Calculate age based on date of birth
                LocalDate dob = investor.getDateOfBirth();
                LocalDate currentDate = LocalDate.now();
                int age = Period.between(dob, currentDate).getYears();

                // Generate age statement
                String ageStatement = "The investor is " + age + " years old.";

                // Calculate 90% of the amount
                double ninetyPercent = amount * 0.90;

                WithdrawalDetails withdrawalDetails = new WithdrawalDetails();
                withdrawalDetails.setNinetyPercentAmount(ninetyPercent);
                withdrawalDetails.setAgeStatement(ageStatement);

                return Optional.of(withdrawalDetails);
            }
        }

        return Optional.empty(); // Withdrawal notice or investor not found
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
