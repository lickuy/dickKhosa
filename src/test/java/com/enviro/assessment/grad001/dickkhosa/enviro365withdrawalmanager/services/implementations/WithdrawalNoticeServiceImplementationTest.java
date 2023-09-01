package com.enviro.assessment.grad001.dickkhosa.enviro365withdrawalmanager.services.implementations;

import com.enviro.assessment.grad001.dickkhosa.enviro365withdrawalmanager.entities.Investor;
import com.enviro.assessment.grad001.dickkhosa.enviro365withdrawalmanager.entities.WithdrawalNotice;
import com.enviro.assessment.grad001.dickkhosa.enviro365withdrawalmanager.repositories.WithdrawalNoticeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class WithdrawalNoticeServiceImplementationTest {

    @Mock
    private WithdrawalNoticeRepository withdrawalNoticeRepository;

    @InjectMocks
    private WithdrawalNoticeServiceImplementation withdrawalNoticeService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCreateWithdrawalNotice() {
        // Create a sample withdrawal notice
        WithdrawalNotice withdrawalNotice = new WithdrawalNotice();
        withdrawalNotice.setId(1L);

        // Mock the repository save method
        when(withdrawalNoticeRepository.save(withdrawalNotice)).thenReturn(withdrawalNotice);

        // Call the service method
        WithdrawalNotice createdNotice = withdrawalNoticeService.createWithdrawalNotice(withdrawalNotice);

        // Verify the save method was called and the returned object is not null
        verify(withdrawalNoticeRepository, times(1)).save(withdrawalNotice);
        assertNotNull(createdNotice);

        // Check if the created notice matches the original one
        assertEquals(withdrawalNotice.getId(), createdNotice.getId());
    }

    @Test
    public void testGetWithdrawalNoticesForInvestor() {
        // Create a sample investor
        Investor investor = new Investor();
        investor.setId(1L);

        // Create a list of withdrawal notices
        List<WithdrawalNotice> withdrawalNotices = new ArrayList<>();
        WithdrawalNotice notice1 = new WithdrawalNotice();
        notice1.setId(1L);
        notice1.setInvestor();
        withdrawalNotices.add(notice1);

        WithdrawalNotice notice2 = new WithdrawalNotice();
        notice2.setId(2L);
        notice2.setInvestor();
        withdrawalNotices.add(notice2);

        // Mock the repository findByInvestor method
        when(withdrawalNoticeRepository.findByInvestor(investor)).thenReturn(withdrawalNotices);

        // Call the service method
        List<WithdrawalNotice> foundNotices = withdrawalNoticeService.getWithdrawalNoticesForInvestor(investor);

        // Verify the findByInvestor method was called and the returned list is not empty
        verify(withdrawalNoticeRepository, times(1)).findByInvestor(investor);
        assertFalse(foundNotices.isEmpty());

        // Check if the found notices match the original ones
        assertEquals(withdrawalNotices.size(), foundNotices.size());
        assertEquals(notice1.getId(), foundNotices.get(0).getId());
        assertEquals(notice2.getId(), foundNotices.get(1).getId());
    }

    @Test
    public void testGetWithdrawalNoticeByIdFound() {
        // Create a sample withdrawal notice
        WithdrawalNotice withdrawalNotice = new WithdrawalNotice();
        withdrawalNotice.setId(1L);

        // Mock the repository findById method
        when(withdrawalNoticeRepository.findById(1L)).thenReturn(Optional.of(withdrawalNotice));

        // Call the service method
        Optional<WithdrawalNotice> foundNotice = withdrawalNoticeService.getWithdrawalNoticeById(1L);

        // Verify the findById method was called and the returned Optional is not empty
        verify(withdrawalNoticeRepository, times(1)).findById(1L);
        assertTrue(foundNotice.isPresent());

        // Check if the found notice matches the original one
        assertEquals(withdrawalNotice.getId(), foundNotice.get().getId());
    }

    @Test
    public void testGetWithdrawalNoticeByIdNotFound() {
        // Mock the repository findById method to return an empty Optional
        when(withdrawalNoticeRepository.findById(1L)).thenReturn(Optional.empty());

        // Call the service method
        Optional<WithdrawalNotice> foundNotice = withdrawalNoticeService.getWithdrawalNoticeById(1L);

        // Verify the findById method was called and the returned Optional is empty
        verify(withdrawalNoticeRepository, times(1)).findById(1L);
        assertFalse(foundNotice.isPresent());
    }

    @Test
    public void testGetAllWithdrawalNotices() {
        // Create a list of withdrawal notices
        List<WithdrawalNotice> withdrawalNotices = new ArrayList<>();
        WithdrawalNotice notice1 = new WithdrawalNotice();
        notice1.setId(1L);
        withdrawalNotices.add(notice1);

        WithdrawalNotice notice2 = new WithdrawalNotice();
        notice2.setId(2L);
        withdrawalNotices.add(notice2);

        // Mock the repository findAll method
        when(withdrawalNoticeRepository.findAll()).thenReturn(withdrawalNotices);

        // Call the service method
        List<WithdrawalNotice> foundNotices = withdrawalNoticeService.getAllWithdrawalNotices();

        // Verify the findAll method was called and the returned list is not empty
        verify(withdrawalNoticeRepository, times(1)).findAll();
        assertFalse(foundNotices.isEmpty());

        // Check if the found notices match the original ones
        assertEquals(withdrawalNotices.size(), foundNotices.size());
        assertEquals(notice1.getId(), foundNotices.get(0).getId());
        assertEquals(notice2.getId(), foundNotices.get(1).getId());
    }
}
