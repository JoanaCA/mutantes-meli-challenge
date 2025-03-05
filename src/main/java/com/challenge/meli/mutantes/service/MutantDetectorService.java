package com.challenge.meli.mutantes.service;

import com.challenge.meli.mutantes.model.DnaRecord;
import com.challenge.meli.mutantes.repository.DnaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class MutantDetectorService {

    @Autowired
    private DnaRepository dnaRepository;

    public boolean isMutant(String[] dna){

        // Validar que el ADN no sea nulo
        if (dna == null) {
            throw new IllegalArgumentException("El ADN no puede ser nulo");
        }

        // Validar que el ADN sea una matriz cuadrada
        int n = dna.length;
        for (String row : dna) {
            if (row == null || row.length() != n) {
                throw new IllegalArgumentException("El ADN debe ser una matriz cuadrada");
            }
        }

        // Validar que el ADN solo contenga caracteres válidos (A, T, C, G)
        for (String row : dna) {
            for (char c : row.toCharArray()) {
                if (c != 'A' && c != 'T' && c != 'C' && c != 'G') {
                    throw new IllegalArgumentException("El ADN contiene caracteres inválidos: " + c);
                }
            }
        }

        // Verificar si el ADN ya ha sido analizado
        String dnaString = String.join(",", dna);
        if (dnaRepository.existsByDna(dnaString)) {
            throw new RuntimeException("DNA already checked");
        }

        // Detectar si el ADN es mutante
        boolean isMutant = checkMutantLogic(dna);

        // Guardar el resultado en la base de datos
        DnaRecord record = new DnaRecord();
        record.setDna(dnaString);
        record.setMutant(isMutant);
        dnaRepository.save(record);

        return isMutant;
    }

    private boolean checkMutantLogic(String[] dna) {
        int n = dna.length;
        int count = 0;

        //verifica h y v
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n - 3; j++) {
                // h...
                if (dna[i].charAt(j) == dna[i].charAt(j + 1) &&
                        dna[i].charAt(j) == dna[i].charAt(j + 2) &&
                        dna[i].charAt(j) == dna[i].charAt(j + 3)) {
                    count++;
                }
                //v...
                if (dna[j].charAt(i) == dna[j + 1].charAt(i) &&
                        dna[j].charAt(i) == dna[j + 2].charAt(i) &&
                        dna[j].charAt(i) == dna[j + 3].charAt(i)) {
                    count++;
                }
                if (count > 1) {
                    return true;

                }
            }
        }

        // Verificar diagonales
        for (int i = 0; i < n - 3; i++) {
            for (int j = 0; j < n - 3; j++) {
                // Diagonal principal
                if (dna[i].charAt(j) == dna[i + 1].charAt(j + 1) &&
                        dna[i].charAt(j) == dna[i + 2].charAt(j + 2) &&
                        dna[i].charAt(j) == dna[i + 3].charAt(j + 3)) {
                    count++;
                }
                // Diagonal secundaria
                if (dna[i].charAt(j + 3) == dna[i + 1].charAt(j + 2) &&
                        dna[i].charAt(j + 3) == dna[i + 2].charAt(j + 1) &&
                        dna[i].charAt(j + 3) == dna[i + 3].charAt(j)) {
                    count++;
                }
                if (count > 1) {
                    return true;
                }
            }
        }

        return false;

    }

    public Map<String, Object> getStats() {
        long countMutantDna = dnaRepository.countByIsMutant(true);  //
        long countHumanDna = dnaRepository.countByIsMutant(false); //
        double ratio = (double) countMutantDna / (countMutantDna + countHumanDna);  //

        //
        return Map.of(
                "count_mutant_dna", countMutantDna,
                "count_human_dna", countHumanDna,
                "ratio", ratio
        );
    }

}
