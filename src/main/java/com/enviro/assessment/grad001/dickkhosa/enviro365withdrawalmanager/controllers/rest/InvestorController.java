package com.enviro.assessment.grad001.dickkhosa.enviro365withdrawalmanager.controllers.rest;

import com.enviro.assessment.grad001.dickkhosa.enviro365withdrawalmanager.entities.Investor;
import com.enviro.assessment.grad001.dickkhosa.enviro365withdrawalmanager.entities.Product;
import com.enviro.assessment.grad001.dickkhosa.enviro365withdrawalmanager.entities.WithdrawalNotice;
import com.enviro.assessment.grad001.dickkhosa.enviro365withdrawalmanager.exceptions.NotFoundException;
import com.enviro.assessment.grad001.dickkhosa.enviro365withdrawalmanager.services.interfaces.InvestorService;
import com.enviro.assessment.grad001.dickkhosa.enviro365withdrawalmanager.services.interfaces.ProductService;
import com.enviro.assessment.grad001.dickkhosa.enviro365withdrawalmanager.services.interfaces.WithdrawalNoticeService;
import lombok.extern.log4j.Log4j2;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

/**
 * REST controller for managing investor-related operations.
 *
 * @author Dick Khosa
 * @since August 28, 2023
 */
@RestController
@RequestMapping("/investors")
@Log4j2
public class InvestorController {

    private final InvestorService investorService;
    private final ProductService productService;
    private final WithdrawalNoticeService withdrawalNoticeService;

    @Autowired
    public InvestorController(InvestorService investorService, ProductService productService,
                              WithdrawalNoticeService withdrawalNoticeService) {
        this.investorService = investorService;
        this.productService = productService;
        this.withdrawalNoticeService = withdrawalNoticeService;
    }

    /**
     * Creates a new investor.
     *
     * @param investor The investor object to create.
     * @return The created investor.
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Investor createInvestor(@RequestBody Investor investor) {
        return investorService.createInvestor(investor);
    }

    /**
     * Retrieves an investor by their ID.
     *
     * @param id The ID of the investor to retrieve.
     * @return The investor if found, or throws a NotFoundException if not found.
     */
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Investor getInvestorById(@PathVariable Long id) {
        return investorService.getInvestorById(id)
                .orElseThrow(() -> new NotFoundException("Investor with ID " + id + " not found"));
    }

    /**
     * Updates an existing investor.
     *
     * @param id             The ID of the investor to update.
     * @param updatedInvestor The updated investor object.
     * @return The updated investor.
     */
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Investor updateInvestor(@PathVariable Long id, @RequestBody Investor updatedInvestor) {
        return investorService.updateInvestor(id, updatedInvestor);
    }

    /**
     * Archives an investor.
     *
     * @param id The ID of the investor to archive.
     * @return The archived investor.
     */
    @PatchMapping("/archive/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Investor archiveInvestor(@PathVariable Long id) {
        return investorService.archiveInvestor(id);
    }

    /**
     * Retrieves the products associated with an investor.
     *
     * @param id The ID of the investor.
     * @return The list of products associated with the investor.
     */
    @GetMapping("/{id}/products")
    @ResponseStatus(HttpStatus.OK)
    public List<Product> getProductsForInvestor(@PathVariable Long id) {
        Investor investor = investorService.getInvestorById(id)
                .orElseThrow(() -> new NotFoundException("Investor with ID " + id + " not found"));
        return productService.getProductsForInvestor(investor);
    }

    /**
     * Creates a withdrawal notice for an investor.
     *
     * @param id               The ID of the investor.
     * @param withdrawalNotice The withdrawal notice to create.
     * @return The created withdrawal notice.
     */
    @PostMapping("/{id}/withdrawal-notices")
    @ResponseStatus(HttpStatus.CREATED)
    public WithdrawalNotice createWithdrawalNotice(@PathVariable Long id,
                                                   @RequestBody WithdrawalNotice withdrawalNotice) {
        return withdrawalNoticeService.createWithdrawalNotice(withdrawalNotice);
    }

    /**
     * Retrieves the withdrawal notices for an investor.
     *
     * @param id The ID of the investor.
     * @return The list of withdrawal notices for the investor.
     */
    @GetMapping("/{id}/withdrawal-notices")
    @ResponseStatus(HttpStatus.OK)
    public List<WithdrawalNotice> getWithdrawalNoticesForInvestor(@PathVariable Long id) {
        Investor investor = investorService.getInvestorById(id)
                .orElseThrow(() -> new NotFoundException("Investor with ID " + id + " not found"));
        return withdrawalNoticeService.getWithdrawalNoticesForInvestor(investor);
    }

    /**
     * Retrieves the balance of products associated with an investor.
     *
     * @param id The ID of the investor.
     * @return The balance of products associated with the investor.
     */
    @GetMapping("/{id}/products-balance")
    @ResponseStatus(HttpStatus.OK)
    public Double getInvestorProductsBalance(@PathVariable Long id) {
        Investor investor = investorService.getInvestorById(id)
                .orElseThrow(() -> new NotFoundException("Investor with ID " + id + " not found"));
        return investorService.calculateProductsBalance(investor);
    }

    /**
     * Downloads withdrawal statements for an investor in Excel format.
     *
     * @param id        The ID of the investor.
     * @param startDate The start date for the statements.
     * @param endDate   The end date for the statements.
     * @return A ResponseEntity containing the Excel file.
     */
    @GetMapping("/{id}/download-statements")
    public ResponseEntity<InputStreamResource> downloadStatements(
            @PathVariable Long id,
            @RequestParam String startDate,
            @RequestParam String endDate) {
        Investor investor = investorService.getInvestorById(id)
                .orElseThrow(() -> new NotFoundException("Investor with ID " + id + " not found"));
        byte[] csvData = generateCSVData(investor, startDate, endDate);

        return ResponseEntity.ok()
                .header("Content-Disposition", "attachment; filename=withdrawal_statements.xlsx")
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(new InputStreamResource(new ByteArrayInputStream(csvData)));
    }

    /**
     * Generates withdrawal statements in Excel format.
     *
     * @param investor   The investor for whom to generate statements.
     * @param startDate  The start date for the statements.
     * @param endDate    The end date for the statements.
     * @return The generated Excel file as a byte array.
     */
    private byte[] generateCSVData(Investor investor, String startDate, String endDate) {
        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("WithdrawalStatements");
            int rowNum = 0;

            // Create header row
            Row headerRow = sheet.createRow(rowNum++);
            headerRow.createCell(0).setCellValue("Product ID");
            headerRow.createCell(1).setCellValue("Product Type");
            headerRow.createCell(2).setCellValue("Product Name");
            headerRow.createCell(3).setCellValue("Current Balance");

            List<Product> products = investor.getProducts();

            // Create data rows
            for (Product product : products) {
                Row dataRow = sheet.createRow(rowNum++);
                dataRow.createCell(0).setCellValue(product.getId());
                dataRow.createCell(1).setCellValue(product.getType().toString());
                dataRow.createCell(2).setCellValue(product.getName());
                dataRow.createCell(3).setCellValue(product.getCurrentBalance());
            }

            // Write workbook to ByteArrayOutputStream
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            workbook.write(outputStream);
            return outputStream.toByteArray();
        } catch (IOException e) {
            log.error("Error generating CSV data", e);
            return new byte[0];
        }
    }
}
