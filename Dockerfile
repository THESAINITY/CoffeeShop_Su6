# Step 1: Build stage
FROM gradle:8.8-jdk21 AS build
WORKDIR /app
COPY . .

# give permission to gradlew
RUN chmod +x ./gradlew

RUN ./gradlew clean build -x test

# Step 2: Runtime stage
FROM eclipse-temurin:21-jdk
WORKDIR /app
COPY --from=build /app/build/libs/*.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
