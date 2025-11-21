package org.example.service;

import org.example.DTO.StatsResponse;
import org.example.Repository.DnaRecordRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class StatsServiceTest {

    @Mock
    private DnaRecordRepository dnaRecordRepository;

    @InjectMocks
    private StatsService statsService;

    @Test
    public void testGetStats() {
        // Simulamos que hay 3 mutantes y 2 humanos en la base de datos
        when(dnaRecordRepository.countByIsMutant(true)).thenReturn(3L);
        when(dnaRecordRepository.countByIsMutant(false)).thenReturn(2L);

        StatsResponse response = statsService.getStats();
        assertEquals(3, response.getCountMutantDna());
        assertEquals(2, response.getCountHumanDna());
        assertEquals(1.5, response.getRatio());
    }

    @Test
    public void testGetStats_NoHumans() {
        // Simulamos que no hay humanos (evitar división por cero)
        when(dnaRecordRepository.countByIsMutant(true)).thenReturn(1L);
        when(dnaRecordRepository.countByIsMutant(false)).thenReturn(0L);

        StatsResponse response = statsService.getStats();
        assertEquals(1, response.getCountMutantDna());
        assertEquals(0, response.getCountHumanDna());
        assertEquals(0, response.getRatio());
    }
}
