package org.example.Service;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MutantDetector {

    private static final int SEQUENCE_LENGTH = 4;

    public boolean detectMutant(List<String> dna) {
        if (dna == null || dna.isEmpty()) {
            return false;
        }

        int n = dna.size();
        int count = 0;
        char[][] matrix = convertToMatrix(dna);

        // Buscar secuencias horizontales
        count += checkHorizontal(matrix, n);
        if (count > 1) return true;

        // Buscar secuencias verticales
        count += checkVertical(matrix, n);
        if (count > 1) return true;

        // Buscar secuencias diagonales (\)
        count += checkDiagonalDown(matrix, n);
        if (count > 1) return true;

        // Buscar secuencias diagonales (/)
        count += checkDiagonalUp(matrix, n);
        return count > 1;
    }

    private char[][] convertToMatrix(List<String> dna) {
        int n = dna.size();
        char[][] matrix = new char[n][n];
        for (int i = 0; i < n; i++) {
            matrix[i] = dna.get(i).toCharArray();
        }
        return matrix;
    }

    private int checkHorizontal(char[][] matrix, int n) {
        int count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= n - SEQUENCE_LENGTH; j++) {
                if (matrix[i][j] == matrix[i][j + 1] &&
                        matrix[i][j] == matrix[i][j + 2] &&
                        matrix[i][j] == matrix[i][j + 3]) {
                    count++;
                    if (count > 1) return count;
                }
            }
        }
        return count;
    }

    private int checkVertical(char[][] matrix, int n) {
        int count = 0;
        for (int i = 0; i <= n - SEQUENCE_LENGTH; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == matrix[i + 1][j] &&
                        matrix[i][j] == matrix[i + 2][j] &&
                        matrix[i][j] == matrix[i + 3][j]) {
                    count++;
                    if (count > 1) return count;
                }
            }
        }
        return count;
    }

    private int checkDiagonalDown(char[][] matrix, int n) {
        int count = 0;
        for (int i = 0; i <= n - SEQUENCE_LENGTH; i++) {
            for (int j = 0; j <= n - SEQUENCE_LENGTH; j++) {
                if (matrix[i][j] == matrix[i + 1][j + 1] &&
                        matrix[i][j] == matrix[i + 2][j + 2] &&
                        matrix[i][j] == matrix[i + 3][j + 3]) {
                    count++;
                    if (count > 1) return count;
                }
            }
        }
        return count;
    }

    private int checkDiagonalUp(char[][] matrix, int n) {
        int count = 0;
        for (int i = SEQUENCE_LENGTH - 1; i < n; i++) {
            for (int j = 0; j <= n - SEQUENCE_LENGTH; j++) {
                if (matrix[i][j] == matrix[i - 1][j + 1] &&
                        matrix[i][j] == matrix[i - 2][j + 2] &&
                        matrix[i][j] == matrix[i - 3][j + 3]) {
                    count++;
                    if (count > 1) return count;
                }
            }
        }
        return count;
    }
}