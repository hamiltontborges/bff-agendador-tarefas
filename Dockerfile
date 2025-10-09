FROM maven:3.9.11-eclipse-temurin-17 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

FROM eclipse-temurin:17-jdk
WORKDIR /app
COPY --from=build /app/target/*.jar app/bff-agendador.jar
EXPOSE 8083
CMD ["java", "-jar", "app/bff-agendador.jar"]
