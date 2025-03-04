package com.challenge.meli.mutantes.controller;

import com.challenge.meli.mutantes.model.DnaRequest;
import com.challenge.meli.mutantes.model.MutantResponse;
import com.challenge.meli.mutantes.service.MutantDetectorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/mutant")
public class MutantController {

    @Autowired
    private MutantDetectorService mutantDetectorService;

    @PostMapping
    public ResponseEntity<MutantResponse> checkMutant(@RequestBody DnaRequest dnaRequest) {

        String[] dna = dnaRequest.getDna();
        boolean isMutant = mutantDetectorService.isMutant(dnaRequest.getDna());
        if (isMutant) {
            MutantResponse response = new MutantResponse("¡Es un mutante!", dna, true);
            return ResponseEntity.ok(response);
        } else {
            MutantResponse response = new MutantResponse("No es un mutante.", dna, false);
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
        }
    }

    @GetMapping("/stats")
    public ResponseEntity<Map<String, Object>> getStats() {
        Map<String, Object> stats = mutantDetectorService.getStats();
        return ResponseEntity.ok(stats);
    }
}
