package com.enviro.assessment.grad001.dickkhosa.enviro365withdrawalmanager.controllers.rest;

import com.enviro.assessment.grad001.dickkhosa.enviro365withdrawalmanager.entities.Investor;
import com.enviro.assessment.grad001.dickkhosa.enviro365withdrawalmanager.entities.Product;
import com.enviro.assessment.grad001.dickkhosa.enviro365withdrawalmanager.services.interfaces.InvestorService;
import com.enviro.assessment.grad001.dickkhosa.enviro365withdrawalmanager.services.interfaces.ProductService;
import com.enviro.assessment.grad001.dickkhosa.enviro365withdrawalmanager.services.interfaces.WithdrawalNoticeService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class InvestorControllerTest {

    @Test
    public void test_create_investor_returns_created_investor() {
        // Arrange
        InvestorService investorService = Mockito.mock(InvestorService.class);
        ProductService productService = Mockito.mock(ProductService.class);
        WithdrawalNoticeService withdrawalNoticeService = Mockito.mock(WithdrawalNoticeService.class);
        InvestorController investorController = new InvestorController(investorService, productService, withdrawalNoticeService);
        Investor investor = new Investor();
        when(investorService.createInvestor(any(Investor.class))).thenReturn(investor);

        // Act
        Investor result = investorController.createInvestor(investor);

        // Assert
        assertEquals(investor, result);
        verify(investorService, times(1)).createInvestor(any(Investor.class));
    }

    @Test
    public void test_get_investor_by_id_returns_investor() {
        // Arrange
        InvestorService investorService = Mockito.mock(InvestorService.class);
        ProductService productService = Mockito.mock(ProductService.class);
        WithdrawalNoticeService withdrawalNoticeService = Mockito.mock(WithdrawalNoticeService.class);
        InvestorController investorController = new InvestorController(investorService, productService, withdrawalNoticeService);
        Long id = 1L;
        Investor investor = new Investor();
        when(investorService.getInvestorById(id)).thenReturn(Optional.of(investor));

        // Act
        Investor result = investorController.getInvestorById(id);

        // Assert
        assertEquals(investor, result);
        verify(investorService, times(1)).getInvestorById(id);
    }

    @Test
    public void test_update_investor_returns_updated_investor() {
        // Arrange
        InvestorService investorService = Mockito.mock(InvestorService.class);
        ProductService productService = Mockito.mock(ProductService.class);
        WithdrawalNoticeService withdrawalNoticeService = Mockito.mock(WithdrawalNoticeService.class);
        InvestorController investorController = new InvestorController(investorService, productService, withdrawalNoticeService);
        Long id = 1L;
        Investor updatedInvestor = new Investor();
        when(investorService.updateInvestor(id, updatedInvestor)).thenReturn(updatedInvestor);

        // Act
        Investor result = investorController.updateInvestor(id, updatedInvestor);

        // Assert
        assertEquals(updatedInvestor, result);
        verify(investorService, times(1)).updateInvestor(id, updatedInvestor);
    }

    @Test
    public void test_archive_investor_returns_archived_investor() {
        // Arrange
        InvestorService investorService = Mockito.mock(InvestorService.class);
        ProductService productService = Mockito.mock(ProductService.class);
        WithdrawalNoticeService withdrawalNoticeService = Mockito.mock(WithdrawalNoticeService.class);
        InvestorController investorController = new InvestorController(investorService, productService, withdrawalNoticeService);
        Long id = 1L;
        Investor archivedInvestor = new Investor();
        when(investorService.archiveInvestor(id)).thenReturn(archivedInvestor);

        // Act
        Investor result = investorController.archiveInvestor(id);

        // Assert
        assertEquals(archivedInvestor, result);
        verify(investorService, times(1)).archiveInvestor(id);
    }

    @Test
    public void test_get_products_for_investor_returns_list_of_products() {
        // Arrange
        InvestorService investorService = Mockito.mock(InvestorService.class);
        ProductService productService = Mockito.mock(ProductService.class);
        WithdrawalNoticeService withdrawalNoticeService = Mockito.mock(WithdrawalNoticeService.class);
        InvestorController investorController = new InvestorController(investorService, productService, withdrawalNoticeService);
        Long id = 1L;
        Investor investor = new Investor();
        List<Product> products = Arrays.asList(new Product(), new Product());
        when(investorService.getInvestorById(id)).thenReturn(Optional.of(investor));
        when(productService.getProductsForInvestor(investor)).thenReturn(products);

        // Act
        List<Product> result = investorController.getProductsForInvestor(id);

        // Assert
        assertEquals(products, result);
        verify(investorService, times(1)).getInvestorById(id);
    }

}
