package com.challenge.meli.mutantes.service;

import com.challenge.meli.mutantes.repository.DnaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class MutantDetectorServiceTest {

    @Mock
    private DnaRepository dnaRepository;

    @InjectMocks
    private MutantDetectorService mutantDetectorService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this); // Inicializa los mocks
    }
    // test para adn mutante
    @Test
    public void testIsMutant_WhenDnaIsMutant_ReturnsTrue() {
        //Arrange: Configura el escenario de prueba
        String[] dna = {"ATGCGA", "CAGTGC", "TTATGT", "AGAAGG", "CCCCTA", "TCACTG"};

        // Act: Ejecuta el método bajo prueba
        boolean isMutant = mutantDetectorService.isMutant(dna);

        //  Assert: Verifica el resultado esperado
        assertTrue(isMutant, "El ADN debería ser mutante");
    }

    // test para adn no mutante
    @Test
    public void testIsMutant_WhenDnaIsNotMutant_ReturnsFalse() {
        //
        String[] dna = {"ATGCGA", "CAGTGC", "TTATTT", "AGACGG", "GCGTCA", "TCACTG"};

        //
        boolean isMutant = mutantDetectorService.isMutant(dna);

        //
        assertFalse(isMutant, "El ADN no debería ser mutante");
    }

    @Test
    public void testIsMutant_WhenDnaIsInvalid_ThrowsException() {

        // Arrange: Configura el escenario de prueba
        String[] dna = {"ATGCGA", "CAGTGC", "TTATXT", "AGACGG", "GCGTCA", "TCACTG"};

        // Act y Assert: Verifica que se lance una excepción
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            mutantDetectorService.isMutant(dna);
        });

        //  Verifica el mensaje de la excepción
        assertTrue(exception.getMessage().contains("caracteres inválidos"));
    }
}
