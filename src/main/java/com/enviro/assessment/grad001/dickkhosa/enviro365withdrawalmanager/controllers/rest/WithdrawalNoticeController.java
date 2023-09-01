package com.enviro.assessment.grad001.dickkhosa.enviro365withdrawalmanager.controllers.rest;

import com.enviro.assessment.grad001.dickkhosa.enviro365withdrawalmanager.entities.Investor;
import com.enviro.assessment.grad001.dickkhosa.enviro365withdrawalmanager.entities.WithdrawalNotice;
import com.enviro.assessment.grad001.dickkhosa.enviro365withdrawalmanager.exceptions.NotFoundException;
import com.enviro.assessment.grad001.dickkhosa.enviro365withdrawalmanager.exceptions.WithdrawalValidationException;
import com.enviro.assessment.grad001.dickkhosa.enviro365withdrawalmanager.services.interfaces.InvestorService;
import com.enviro.assessment.grad001.dickkhosa.enviro365withdrawalmanager.services.interfaces.WithdrawalNoticeService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing withdrawal notice-related operations.
 *
 * @author Dick Khosa
 * @since August 28, 2023
 */
@RestController
@RequestMapping("/withdrawal-notices")
@Log4j2
public class WithdrawalNoticeController {

    private final InvestorService investorService;
    private final WithdrawalNoticeService withdrawalNoticeService;

    @Autowired
    public WithdrawalNoticeController(InvestorService investorService, WithdrawalNoticeService withdrawalNoticeService) {
        this.investorService = investorService;
        this.withdrawalNoticeService = withdrawalNoticeService;
    }

    /**
     * Creates a new withdrawal notice for a specific investor.
     *
     * @param investorId      The ID of the investor for whom the withdrawal notice is created.
     * @param withdrawalNotice The withdrawal notice object to create.
     * @return ResponseEntity with a message indicating the result of the operation and HTTP status CREATED if successful.
     */
    @PostMapping
    public ResponseEntity<String> createWithdrawalNotice(
            @RequestParam Long investorId,
            @RequestBody WithdrawalNotice withdrawalNotice) {
        try {
            Investor investor = investorService.getInvestorById(investorId)
                    .orElseThrow(() -> new NotFoundException("Investor with ID " + investorId + " not found"));

            withdrawalNoticeService.createWithdrawalNotice(withdrawalNotice);
            return ResponseEntity.status(HttpStatus.CREATED).body("Withdrawal notice created successfully");
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Investor not found");
        } catch (WithdrawalValidationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    /**
     * Retrieves a withdrawal notice by its ID.
     *
     * @param id The ID of the withdrawal notice to retrieve.
     * @return ResponseEntity with the retrieved withdrawal notice if found, or HTTP status NOT FOUND if not found.
     */
    @GetMapping("/{id}")
    public ResponseEntity<WithdrawalNotice> getWithdrawalNoticeById(@PathVariable Long id) {
        Optional<WithdrawalNotice> withdrawalNotice = withdrawalNoticeService.getWithdrawalNoticeById(id);
        return withdrawalNotice.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }


    /**
     * Retrieves all withdrawal notices.
     *
     * @return The list of all withdrawal notices.
     */
    @GetMapping
    public List<WithdrawalNotice> getAllWithdrawalNotices() {
        return withdrawalNoticeService.getAllWithdrawalNotices();
    }
}
