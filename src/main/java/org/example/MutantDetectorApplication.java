package org.example;

import org.example.Service.MutantService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MutantDetectorApplication {
    public static void main(String[] args) {
        SpringApplication.run(MutantDetectorApplication.class, args);
    }

    @Bean
    public CommandLineRunner initData(MutantService mutantService) {
        return args -> {
            System.out.println("Aplicación Mutant Detector iniciada correctamente!");
        };
    }
}