package org.example.Service;

import org.example.DTO.DnaRequest;
import org.example.Entity.DnaRecord;
import org.example.Repository.DnaRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@Service
public class MutantService {

    @Autowired
    private DnaRecordRepository dnaRecordRepository;

    @Autowired
    private MutantDetector mutantDetector;

    public boolean isMutant(DnaRequest request) {
        String dnaHash = calculateSHA256(request.getDna());
        DnaRecord existingRecord = dnaRecordRepository.findByDnaHash(dnaHash);

        if (existingRecord != null) {
            return existingRecord.isMutant();
        }

        boolean isMutant = mutantDetector.detectMutant(request.getDna());
        DnaRecord newRecord = new DnaRecord(dnaHash, isMutant);
        dnaRecordRepository.save(newRecord);
        return isMutant;
    }

    private String calculateSHA256(List<String> dna) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            String dnaString = String.join("", dna);
            byte[] hashBytes = digest.digest(dnaString.getBytes());
            StringBuilder hexString = new StringBuilder();
            for (byte b : hashBytes) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error calculating SHA-256", e);
        }
    }
}