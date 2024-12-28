# imagen base de OpenJDK 17
FROM openjdk:17-jdk-slim AS builder

WORKDIR /app

#  archivo JAR de la aplicación Spring Boot al contenedor
COPY target/franquicias-0.0.1-SNAPSHOT.jar franquicias.jar

#  puerto 8080
EXPOSE 8080

ENTRYPOINT ["java", "-jar", "franquicias.jar"]
