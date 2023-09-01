package com.enviro.assessment.grad001.dickkhosa.enviro365withdrawalmanager.controllers.rest;

import com.enviro.assessment.grad001.dickkhosa.enviro365withdrawalmanager.entities.Investor;
import com.enviro.assessment.grad001.dickkhosa.enviro365withdrawalmanager.entities.WithdrawalNotice;
import com.enviro.assessment.grad001.dickkhosa.enviro365withdrawalmanager.exceptions.NotFoundException;
import com.enviro.assessment.grad001.dickkhosa.enviro365withdrawalmanager.exceptions.WithdrawalValidationException;
import com.enviro.assessment.grad001.dickkhosa.enviro365withdrawalmanager.services.interfaces.InvestorService;
import com.enviro.assessment.grad001.dickkhosa.enviro365withdrawalmanager.services.interfaces.WithdrawalNoticeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

public class WithdrawalNoticeControllerTest {

    @Mock
    private InvestorService investorService;

    @Mock
    private WithdrawalNoticeService withdrawalNoticeService;

    @InjectMocks
    private WithdrawalNoticeController withdrawalNoticeController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCreateWithdrawalNoticeSuccess() throws NotFoundException, WithdrawalValidationException {
        // Mocking data
        Long investorId = 1L;
        WithdrawalNotice withdrawalNotice = new WithdrawalNotice();
        Investor investor = new Investor();
        investor.setId(investorId);

        // Mocking service methods
        when(investorService.getInvestorById(investorId)).thenReturn(Optional.of(investor));
        doReturn(null).when(withdrawalNoticeService).createWithdrawalNotice(withdrawalNotice);

        // Perform the test
        ResponseEntity<String> responseEntity = withdrawalNoticeController.createWithdrawalNotice(investorId, withdrawalNotice);

        // Verify the response and service method invocations
        verify(investorService, times(1)).getInvestorById(investorId);
        verify(withdrawalNoticeService, times(1)).createWithdrawalNotice(withdrawalNotice);
        verifyNoMoreInteractions(investorService, withdrawalNoticeService);

        assert(responseEntity.getStatusCode() == HttpStatus.CREATED);
        assert(Objects.equals(responseEntity.getBody(), "Withdrawal notice created successfully"));
    }


    @Test
    public void testCreateWithdrawalNoticeInvestorNotFound() throws NotFoundException {
        // Mocking data
        Long investorId = 1L;
        WithdrawalNotice withdrawalNotice = new WithdrawalNotice();

        // Mocking service method to throw NotFoundException
        when(investorService.getInvestorById(investorId)).thenReturn(Optional.empty());

        // Perform the test
        ResponseEntity<String> responseEntity = withdrawalNoticeController.createWithdrawalNotice(investorId, withdrawalNotice);

        // Verify the response and service method invocations
        verify(investorService, times(1)).getInvestorById(investorId);
        verifyNoMoreInteractions(investorService, withdrawalNoticeService);

        assert(responseEntity.getStatusCode() == HttpStatus.NOT_FOUND);
        assert(Objects.equals(responseEntity.getBody(), "Investor not found"));
    }

    @Test
    public void testCreateWithdrawalNoticeValidationException() throws NotFoundException, WithdrawalValidationException {
        // Mocking data
        Long investorId = 1L;
        WithdrawalNotice withdrawalNotice = new WithdrawalNotice();
        Investor investor = new Investor();
        investor.setId(investorId);

        // Mocking service methods to throw WithdrawalValidationException
        when(investorService.getInvestorById(investorId)).thenReturn(Optional.of(investor));
        doThrow(new WithdrawalValidationException("Validation failed")).when(withdrawalNoticeService).createWithdrawalNotice(withdrawalNotice);

        // Perform the test
        ResponseEntity<String> responseEntity = withdrawalNoticeController.createWithdrawalNotice(investorId, withdrawalNotice);

        // Verify the response and service method invocations
        verify(investorService, times(1)).getInvestorById(investorId);
        verify(withdrawalNoticeService, times(1)).createWithdrawalNotice(withdrawalNotice);
        verifyNoMoreInteractions(investorService, withdrawalNoticeService);

        assert(responseEntity.getStatusCode() == HttpStatus.BAD_REQUEST);
        assert(Objects.equals(responseEntity.getBody(), "Validation failed"));
    }

    @Test
    public void testGetWithdrawalNoticeByIdFound() {
        // Mocking data
        Long withdrawalNoticeId = 1L;
        WithdrawalNotice withdrawalNotice = new WithdrawalNotice();
        withdrawalNotice.setId(withdrawalNoticeId);

        // Mocking service method
        when(withdrawalNoticeService.getWithdrawalNoticeById(withdrawalNoticeId)).thenReturn(Optional.of(withdrawalNotice));

        // Perform the test
        ResponseEntity<WithdrawalNotice> responseEntity = withdrawalNoticeController.getWithdrawalNoticeById(withdrawalNoticeId);

        // Verify the response and service method invocations
        verify(withdrawalNoticeService, times(1)).getWithdrawalNoticeById(withdrawalNoticeId);
        verifyNoMoreInteractions(investorService, withdrawalNoticeService);

        assert(responseEntity.getStatusCode() == HttpStatus.OK);
        assert(Objects.equals(responseEntity.getBody(), withdrawalNotice));
    }

    @Test
    public void testGetWithdrawalNoticeByIdNotFound() {
        // Mocking data
        Long withdrawalNoticeId = 1L;

        // Mocking service method to return empty Optional
        when(withdrawalNoticeService.getWithdrawalNoticeById(withdrawalNoticeId)).thenReturn(Optional.empty());

        // Perform the test
        ResponseEntity<WithdrawalNotice> responseEntity = withdrawalNoticeController.getWithdrawalNoticeById(withdrawalNoticeId);

        // Verify the response and service method invocations
        verify(withdrawalNoticeService, times(1)).getWithdrawalNoticeById(withdrawalNoticeId);
        verifyNoMoreInteractions(investorService, withdrawalNoticeService);

        assert(responseEntity.getStatusCode() == HttpStatus.NOT_FOUND);
    }

    @Test
    public void testGetAllWithdrawalNotices() {
        // Mocking data
        List<WithdrawalNotice> withdrawalNotices = new ArrayList<>();
        withdrawalNotices.add(new WithdrawalNotice());
        withdrawalNotices.add(new WithdrawalNotice());

        // Mocking service method
        when(withdrawalNoticeService.getAllWithdrawalNotices()).thenReturn(withdrawalNotices);

        // Perform the test
        List<WithdrawalNotice> result = withdrawalNoticeController.getAllWithdrawalNotices();

        // Verify the response and service method invocations
        verify(withdrawalNoticeService, times(1)).getAllWithdrawalNotices();
        verifyNoMoreInteractions(investorService, withdrawalNoticeService);

        assert(result.size() == 2);
    }
}
