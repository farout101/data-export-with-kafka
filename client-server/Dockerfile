# Build stage
FROM eclipse-temurin:17-jdk-jammy as builder
WORKDIR /client_app
COPY .mvn/ .mvn
COPY mvnw pom.xml ./
RUN ./mvnw dependency:go-offline
COPY src ./src
RUN ./mvnw package -DskipTests

# Package stage
FROM eclipse-temurin:17-jre-jammy
WORKDIR /client_app
COPY --from=builder /client_app/target/*.jar client_app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","client_app.jar"]
