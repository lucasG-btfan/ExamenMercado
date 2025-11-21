package org.example.Repository;

import org.example.Entity.DnaRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DnaRecordRepository extends JpaRepository<DnaRecord, Long> {
    DnaRecord findByDnaHash(String dnaHash); // Cambiado de findByHash a findByDnaHash
    long countByIsMutant(boolean isMutant);
}