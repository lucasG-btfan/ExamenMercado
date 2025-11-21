package org.example.Validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.List;

public class ValidDnaSequenceValidator implements ConstraintValidator<ValidDnaSequence, List<String>> {

    private static final int MIN_SIZE = 4;
    private static final int MAX_SIZE = 6;

    @Override
    public void initialize(ValidDnaSequence constraintAnnotation) {
    }

    @Override
    public boolean isValid(List<String> dna, ConstraintValidatorContext context) {
        if (dna == null || dna.isEmpty()) {
            addConstraintViolation(context, "El array de ADN no puede estar vacío");
            return false;
        }

        int size = dna.size();

        // Validar que sea matriz NxN con tamaño permitido
        if (size != MIN_SIZE && size != MAX_SIZE && size != 5) {
            addConstraintViolation(context,
                    "El tamaño de la matriz debe ser 4x4, 5x5 o 6x6. Recibido: " + size + "x?");
            return false;
        }

        // Validar que sea cuadrada (NxN) y caracteres válidos
        for (int i = 0; i < dna.size(); i++) {
            String row = dna.get(i);

            if (row == null) {
                addConstraintViolation(context, "La fila " + i + " no puede ser nula");
                return false;
            }

            if (row.length() != size) {
                addConstraintViolation(context,
                        "Matriz no cuadrada. Fila " + i + " tiene " + row.length() +
                                " caracteres pero debería tener " + size);
                return false;
            }

            // Validar que solo contenga caracteres válidos (A,T,C,G)
            if (!row.matches("[ATCG]+")) {
                addConstraintViolation(context,
                        "La fila " + i + " contiene caracteres inválidos. Solo se permiten: A, T, C, G");
                return false;
            }
        }

        return true;
    }

    private void addConstraintViolation(ConstraintValidatorContext context, String message) {
        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate(message)
                .addConstraintViolation();
    }
}