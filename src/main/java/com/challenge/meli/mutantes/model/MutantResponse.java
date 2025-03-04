package com.challenge.meli.mutantes.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MutantResponse {

    private String message;
    private String[] dna;
    private boolean isMutant;
}
