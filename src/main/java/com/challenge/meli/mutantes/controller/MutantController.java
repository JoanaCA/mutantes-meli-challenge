package com.challenge.meli.mutantes.controller;

import com.challenge.meli.mutantes.model.DnaRequest;
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
    public ResponseEntity<String> checkMutant(@RequestBody DnaRequest dnaRequest) {

        boolean isMutant = mutantDetectorService.isMutant(dnaRequest.getDna());
        if (isMutant) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }

    @GetMapping("/stats")
    public ResponseEntity<Map<String, Object>> getStats() {
        Map<String, Object> stats = mutantDetectorService.getStats();
        return ResponseEntity.ok(stats);
    }
}
