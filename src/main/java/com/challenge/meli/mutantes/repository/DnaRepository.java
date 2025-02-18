package com.challenge.meli.mutantes.repository;

import com.challenge.meli.mutantes.model.DnaRecord;
import com.challenge.meli.mutantes.model.DnaRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

    @Repository
    public interface DnaRepository extends JpaRepository<DnaRecord,Long> {
        boolean existsByDna(String dna);  //

        //
        long countByIsMutant(boolean isMutant);
    }


