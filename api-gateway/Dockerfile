FROM openjdk:21-jdk AS build
WORKDIR /app
COPY .mvn .mvn
COPY mvnw .
COPY pom.xml .
COPY src/ ./src/
RUN ./mvnw clean package -DskipTests

# Используем легковесный образ Java для запуска
FROM eclipse-temurin:21-jre
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]