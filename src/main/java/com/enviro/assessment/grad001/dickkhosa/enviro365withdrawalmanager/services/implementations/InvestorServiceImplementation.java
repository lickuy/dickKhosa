package com.enviro.assessment.grad001.dickkhosa.enviro365withdrawalmanager.services.implementations;

import com.enviro.assessment.grad001.dickkhosa.enviro365withdrawalmanager.entities.Investor;
import com.enviro.assessment.grad001.dickkhosa.enviro365withdrawalmanager.entities.Product;
import com.enviro.assessment.grad001.dickkhosa.enviro365withdrawalmanager.exceptions.NotFoundException;
import com.enviro.assessment.grad001.dickkhosa.enviro365withdrawalmanager.repositories.InvestorRepository;
import com.enviro.assessment.grad001.dickkhosa.enviro365withdrawalmanager.services.interfaces.InvestorService;
import com.enviro.assessment.grad001.dickkhosa.enviro365withdrawalmanager.services.interfaces.WithdrawalNoticeService;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service implementation for managing investors.
 *
 * @author Dick Khosa
 * @since August 28, 2023
 */
@Service
@Slf4j
public class InvestorServiceImplementation implements InvestorService {

    private final InvestorRepository investorRepository;

    @Autowired
    public InvestorServiceImplementation(InvestorRepository investorRepository, WithdrawalNoticeService withdrawalNoticeService) {
        this.investorRepository = investorRepository;
    }

    /**
     * Creates a new investor.
     *
     * @param investor The investor to create.
     * @return The created investor.
     */
    @Override
    public Investor createInvestor(Investor investor) {
        return investorRepository.save(investor);
    }

    /**
     * Retrieves an investor by their ID.
     *
     * @param id The ID of the investor to retrieve.
     * @return An optional containing the investor if found, or empty if not found.
     */
    @Override
    public Optional<Investor> getInvestorById(Long id) {
        return investorRepository.findById(id);
    }

    /**
     * Updates an investor's information by their ID.
     *
     * @param id              The ID of the investor to update.
     * @param updatedInvestor The updated investor information.
     * @return The updated investor.
     * @throws NotFoundException If the investor with the given ID is not found.
     */
    @Override
    public Investor updateInvestor(Long id, Investor updatedInvestor) {
        Optional<Investor> existingInvestor = investorRepository.findById(id);
        if (existingInvestor.isPresent()) {
            Investor investorToUpdate = existingInvestor.get();
            investorToUpdate.setFirstName(updatedInvestor.getFirstName());
            investorToUpdate.setLastName(updatedInvestor.getLastName());
            // ... update other properties as needed
            return investorRepository.save(investorToUpdate);
        } else {
            throw new NotFoundException("Investor with ID " + id + " not found");
        }
    }

    /**
     * Archives an investor by their ID.
     *
     * @param id The ID of the investor to archive.
     * @return The archived investor.
     * @throws NotFoundException If the investor with the given ID is not found.
     */
    @Override
    public Investor archiveInvestor(Long id) {
        Optional<Investor> existingInvestor = investorRepository.findById(id);
        if (existingInvestor.isPresent()) {
            Investor investorToArchive = existingInvestor.get();
            investorToArchive.setArchived(true);
            return investorRepository.save(investorToArchive);
        } else {
            throw new NotFoundException("Investor with ID " + id + " not found");
        }
    }

    /**
     * Calculates the total balance of all products for an investor.
     *
     * @param investor The investor for whom to calculate the balance.
     * @return The total balance.
     */
    @Override
    public Double calculateProductsBalance(Investor investor) {
        List<Product> products = investor.getProducts();
        double balance = 0.0;
        for (Product product : products) {
            balance += product.getCurrentBalance();
        }
        return balance;
    }

    private void createHeaderRow(Row headerRow) {
        Cell cell;
        cell = headerRow.createCell(0);
        cell.setCellValue("Product ID");
        cell = headerRow.createCell(1);
        cell.setCellValue("Product Type");
        cell = headerRow.createCell(2);
        cell.setCellValue("Product Name");
        cell = headerRow.createCell(3);
        cell.setCellValue("Current Balance");
    }

    private void createDataRow(Row dataRow, Product product) {
        Cell cell;
        cell = dataRow.createCell(0);
        cell.setCellValue(product.getId());
        cell = dataRow.createCell(1);
        cell.setCellValue(product.getType().toString());
        cell = dataRow.createCell(2);
        cell.setCellValue(product.getName());
        cell = dataRow.createCell(3);
        cell.setCellValue(product.getCurrentBalance());
    }
}
