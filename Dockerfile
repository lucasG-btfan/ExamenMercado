# ========================================
# ETAPA 1: BUILD (Compilación)
# ========================================
FROM alpine:latest as build

RUN apk update
RUN apk add openjdk17

COPY . .

RUN chmod +x ./gradlew

# Agrega --stacktrace para mejor debugging
RUN ./gradlew bootJar --no-daemon --stacktrace

# ========================================
# ETAPA 2: RUNTIME (Ejecución)
# ========================================
FROM eclipse-temurin:17-jre-alpine

# Agrega un comentario para forzar cambio
EXPOSE 8080

COPY --from=build ./build/libs/Mutantes-1.0-SNAPSHOT.jar ./app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]