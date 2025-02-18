package com.challenge.meli.mutantes.service;

import com.challenge.meli.mutantes.repository.DnaRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class MutantDetectorServiceTest {

    @Mock
    private DnaRepository dnaRepository;

    @InjectMocks
    private MutantDetectorService mutantDetectorService;

    @Test
    public void testIsMutant_WhenDnaIsMutant_ReturnsTrue() {
        //
        String[] dna = {"ATGCGA", "CAGTGC", "TTATGT", "AGAAGG", "CCCCTA", "TCACTG"};

        //
        boolean isMutant = mutantDetectorService.isMutant(dna);

        //
        assertTrue(isMutant);
    }

    @Test
    public void testIsMutant_WhenDnaIsNotMutant_ReturnsFalse() {
        //
        String[] dna = {"ATGCGA", "CAGTGC", "TTATTT", "AGACGG", "GCGTCA", "TCACTG"};

        //
        boolean isMutant = mutantDetectorService.isMutant(dna);

        //
        assertFalse(isMutant);
    }

    @Test
    public void testIsMutant_WhenDnaIsInvalid_ThrowsException() {
        //
        String[] dna = {"ATGCGA", "CAGTGC", "TTATXT", "AGACGG", "GCGTCA", "TCACTG"};  // ADN inválido

        //
        assertThrows(IllegalArgumentException.class, () -> {
            mutantDetectorService.isMutant(dna);
        });
    }
}
