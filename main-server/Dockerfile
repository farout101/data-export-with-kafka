# Build stage
FROM eclipse-temurin:17-jdk-jammy as builder
WORKDIR /main_app
COPY .mvn/ .mvn
COPY mvnw pom.xml ./
RUN ./mvnw dependency:go-offline
COPY src ./src
RUN ./mvnw package -DskipTests

# Package stage
FROM eclipse-temurin:17-jre-jammy
WORKDIR /main_app
COPY --from=builder /main_app/target/*.jar main_app.jar
EXPOSE 8081
ENTRYPOINT ["java","-jar","main_app.jar"]
