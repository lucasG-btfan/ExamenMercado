package org.example.DTO;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.Validation.ValidDnaSequence;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DnaRequest {

    @NotEmpty(message = "El array de ADN no puede estar vacío")
    @ValidDnaSequence
    private List<String> dna;
}