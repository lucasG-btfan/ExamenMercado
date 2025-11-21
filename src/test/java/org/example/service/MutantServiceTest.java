package org.example.service;

import org.example.DTO.DnaRequest;
import org.example.Entity.DnaRecord;
import org.example.Repository.DnaRecordRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class MutantServiceTest {

    @Mock
    private DnaRecordRepository dnaRecordRepository;

    @Mock
    private MutantDetector mutantDetector;

    @InjectMocks
    private MutantService mutantService;

    @Test
    public void testIsMutant_ExistingMutantRecord() {
        // Simulamos que el ADN ya existe y es mutante
        DnaRequest request = new DnaRequest(Arrays.asList("ATGCGA", "CAGTGC", "TTATGT", "AGAAGG", "CCCCTA", "TCACTG"));
        DnaRecord existingRecord = new DnaRecord("hash123", true);
        when(dnaRecordRepository.findByDnaHash(anyString())).thenReturn(existingRecord);

        boolean result = mutantService.isMutant(request);
        assertTrue(result);
        verify(dnaRecordRepository, never()).save(any());
    }

    @Test
    public void testIsMutant_NewMutant() {
        // Simulamos que el ADN es nuevo y es mutante
        DnaRequest request = new DnaRequest(Arrays.asList("ATGCGA", "CAGTGC", "TTATGT", "AGAAGG", "CCCCTA", "TCACTG"));
        when(dnaRecordRepository.findByDnaHash(anyString())).thenReturn(null);
        when(mutantDetector.detectMutant(anyList())).thenReturn(true);

        boolean result = mutantService.isMutant(request);
        assertTrue(result);
        verify(dnaRecordRepository, times(1)).save(any(DnaRecord.class));
    }

    @Test
    public void testIsMutant_NewHuman() {
        // Simulamos que el ADN es nuevo y es humano
        DnaRequest request = new DnaRequest(Arrays.asList("ATGCGA", "CAGTGC", "TTATTT", "AGACGG", "GCGTCA", "TCACTG"));
        when(dnaRecordRepository.findByDnaHash(anyString())).thenReturn(null);
        when(mutantDetector.detectMutant(anyList())).thenReturn(false);

        boolean result = mutantService.isMutant(request);
        assertFalse(result);
        verify(dnaRecordRepository, times(1)).save(any(DnaRecord.class));
    }
}
