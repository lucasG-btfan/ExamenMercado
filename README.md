# 🧬 Mutant Detector API - Guía Completa para Estudiantes

[![Java](https://img.shields.io/badge/Java-17-orange.svg)](https://www.oracle.com/java/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.2.0-brightgreen.svg)](https://spring.io/projects/spring-boot)
[![Gradle](https://img.shields.io/badge/Gradle-8.x-blue.svg)](https://gradle.org/)
[![Tests](https://img.shields.io/badge/Tests-35%20passing-success.svg)]()
[![Coverage](https://img.shields.io/badge/Coverage-90%25-brightgreen.svg)]()

> 📚 **Proyecto Educativo**: API REST para detectar mutantes analizando secuencias de ADN. Desarrollado como examen técnico de MercadoLibre Backend Developer.

---
## Instalación:
# Clonar el proyecto
git clone <tu-repositorio>
cd ExamenMercado

# Compilar y ejecutar
./gradlew bootRun

mi api:  https://mutant-detector-xsof.onrender.com

## Endpoints
# 1. POST /mutant - Verificar si es Mutante
URL: https://mutant-detector-xsof.onrender.com/mutant

Método: POST

Content-Type: application/json

Ejemplos de Uso:
PowerShell (Recomendado para Windows)
powershell
# Mutante - retorna 200 OK
Invoke-RestMethod -Uri "https://mutant-detector-xsof.onrender.com/mutant" `
  -Method POST `
  -Headers @{"Content-Type"="application/json"} `
  -Body '{"dna":["ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"]}'

# Humano - retorna 403 Forbidden
Invoke-RestMethod -Uri "https://mutant-detector-xsof.onrender.com/mutant" `
  -Method POST `
  -Headers @{"Content-Type"="application/json"} `
  -Body '{"dna":["ATGCGA","CAGTGC","TTATTT","AGACGG","GCGTCA","TCACTG"]}'

# Inválido - retorna 400 Bad Request
Invoke-RestMethod -Uri "https://mutant-detector-xsof.onrender.com/mutant" `
  -Method POST `
  -Headers @{"Content-Type"="application/json"} `
  -Body '{"dna":["ATG","CAG","TTA","AGA"]}

  En Postman
Método post 
URL: https://mutant-detector-xsof.onrender.com/mutant 
Headers: Content-Type: application/json 
Body (raw JSON): 
json 
{ "dna": ["ATGCGA", "CAGTGC", "TTATGT", "AGAAGG", "CCCCTA", "TCACTG"] } 
Respuestas: 
# 200 OK: Es mutante (sin cuerpo de respuesta) 
# 403 Forbidden: No es mutante (sin cuerpo de respuesta) 
# 400 Bad Request: DNA inválido (con mensaje de error)

# 2. metodo get
ejemplo de uso (powershell):
 Invoke-RestMethod -Uri "https://mutant-detector-xsof.onrender.com/stats" -Method GET

count_mutant_dna count_human_dna ratio
---------------- --------------- -----
               0               0   0,0

# Validaciones del DNA:

 -Matriz NxN (cuadrada)
- Tamaño mínimo: 4x4
- Solo caracteres válidos: A, T, C, G
- Máximo: 6x6 (configurable)

## ¿Cuándo es Mutante?
Un humano es mutante si encuentra MÁS DE UNA secuencia de 4 letras iguales consecutivas en
- Horizontal (-)
- Vertical (|)
- Diagonal (\/)
