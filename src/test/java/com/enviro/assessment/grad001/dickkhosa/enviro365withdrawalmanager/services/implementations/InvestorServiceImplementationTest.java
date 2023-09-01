package com.enviro.assessment.grad001.dickkhosa.enviro365withdrawalmanager.services.implementations;

import com.enviro.assessment.grad001.dickkhosa.enviro365withdrawalmanager.entities.Investor;
import com.enviro.assessment.grad001.dickkhosa.enviro365withdrawalmanager.exceptions.NotFoundException;
import com.enviro.assessment.grad001.dickkhosa.enviro365withdrawalmanager.repositories.InvestorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class InvestorServiceImplementationTest {

    @Mock
    private InvestorRepository investorRepository;

    @InjectMocks
    private InvestorServiceImplementation investorService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateInvestor() {
        Investor investorToCreate = new Investor();
        when(investorRepository.save(investorToCreate)).thenReturn(investorToCreate);

        Investor createdInvestor = investorService.createInvestor(investorToCreate);

        assertNotNull(createdInvestor);
        verify(investorRepository, times(1)).save(investorToCreate);
    }

    @Test
    public void testGetInvestorByIdSuccess() {
        Long investorId = 1L;
        Investor mockInvestor = new Investor();
        when(investorRepository.findById(investorId)).thenReturn(Optional.of(mockInvestor));

        Optional<Investor> retrievedInvestor = investorService.getInvestorById(investorId);

        assertTrue(retrievedInvestor.isPresent());
        assertEquals(mockInvestor, retrievedInvestor.get());
        verify(investorRepository, times(1)).findById(investorId);
    }

    @Test
    public void testGetInvestorByIdNotFound() {
        Long investorId = 1L;
        when(investorRepository.findById(investorId)).thenReturn(Optional.empty());

        Optional<Investor> retrievedInvestor = investorService.getInvestorById(investorId);

        assertTrue(retrievedInvestor.isEmpty());
        verify(investorRepository, times(1)).findById(investorId);
    }

    @Test
    public void testUpdateInvestorSuccess() {
        Long investorId = 1L;
        Investor existingInvestor = new Investor();
        existingInvestor.setId(investorId);
        Investor updatedInvestor = new Investor();
        updatedInvestor.setFirstName("UpdatedFirstName");
        when(investorRepository.findById(investorId)).thenReturn(Optional.of(existingInvestor));
        when(investorRepository.save(existingInvestor)).thenReturn(existingInvestor);

        Investor result = investorService.updateInvestor(investorId, updatedInvestor);

        assertNotNull(result);
        assertEquals(updatedInvestor.getFirstName(), result.getFirstName());
        verify(investorRepository, times(1)).findById(investorId);
        verify(investorRepository, times(1)).save(existingInvestor);
    }

    @Test
    public void testUpdateInvestorNotFound() {
        Long investorId = 1L;
        Investor updatedInvestor = new Investor();
        when(investorRepository.findById(investorId)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> investorService.updateInvestor(investorId, updatedInvestor));

        verify(investorRepository, times(1)).findById(investorId);
        verify(investorRepository, never()).save(any(Investor.class));
    }

    @Test
    public void testArchiveInvestorSuccess() {
        Long investorId = 1L;
        Investor existingInvestor = new Investor();
        existingInvestor.setId(investorId);
        when(investorRepository.findById(investorId)).thenReturn(Optional.of(existingInvestor));
        when(investorRepository.save(existingInvestor)).thenReturn(existingInvestor);

        Investor archivedInvestor = investorService.archiveInvestor(investorId);

        assertNotNull(archivedInvestor);
        assertTrue(archivedInvestor.isArchive());
        verify(investorRepository, times(1)).findById(investorId);
        verify(investorRepository, times(1)).save(existingInvestor);
    }

    @Test
    public void testArchiveInvestorNotFound() {
        Long investorId = 1L;
        when(investorRepository.findById(investorId)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> investorService.archiveInvestor(investorId));

        verify(investorRepository, times(1)).findById(investorId);
        verify(investorRepository, never()).save(any(Investor.class));
    }

    // You can add more test cases for other methods here
}
