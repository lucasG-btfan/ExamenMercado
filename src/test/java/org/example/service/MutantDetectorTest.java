package org.example.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MutantDetectorTest {

    @Autowired
    private MutantDetector mutantDetector;

    @Test
    void whenMutantDna_thenReturnTrue() {
        String[] mutantDna = {
                "ATGCGA",
                "CAGTGC",
                "TTATGT",
                "AGAAGG",
                "CCCCTA",
                "TCACTG"
        };

        assertTrue(mutantDetector.detectMutant(mutantDna));
    }

    @Test
    void whenHumanDna_thenReturnFalse() {
        String[] humanDna = {
                "ATGCGA",
                "CAGTGC",
                "TTATTT",
                "AGACGG",
                "GCGTCA",
                "TCACTG"
        };

        assertFalse(mutantDetector.detectMutant(humanDna));
    }
}