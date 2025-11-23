package org.example.Controller;

import jakarta.validation.Valid;
import org.example.DTO.DnaRequest;
import org.example.Service.MutantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class MutantController {

    @Autowired
    private MutantService mutantService;

    @PostMapping("/mutant")
    public ResponseEntity<Void> isMutant(@RequestBody @Valid DnaRequest request) {
        boolean isMutant = mutantService.isMutant(request);

        if (isMutant) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }
}