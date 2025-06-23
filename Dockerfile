FROM amazoncorretto:21.0.4-alpine3.18 AS builder

# Set the working directory inside the container
WORKDIR /app

# Copy your compiled Java application JAR file into the container
COPY . .

RUN chmod +x mvnw

# Build the app
RUN ./mvnw clean package -DskipTests

# Run app
FROM amazoncorretto:21.0.4-alpine3.18

# Set the working directory
WORKDIR /app

COPY --from=builder /app/target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]