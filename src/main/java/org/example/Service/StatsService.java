package org.example.Service;

import org.example.DTO.StatsResponse;
import org.example.Repository.DnaRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StatsService {

    @Autowired
    private DnaRecordRepository dnaRecordRepository;

    public StatsResponse getStats() {
        long totalMutants = dnaRecordRepository.countByIsMutant(true);
        long totalHumans = dnaRecordRepository.countByIsMutant(false);
        double ratio = totalHumans == 0 ? 0 : (double) totalMutants / totalHumans;
        return new StatsResponse(totalMutants, totalHumans, ratio);
    }
}