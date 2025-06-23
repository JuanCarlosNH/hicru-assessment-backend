FROM amazoncorretto:21.0.4-alpine3.18

# Set the working directory inside the container
WORKDIR /app

# Copy the JAR file into the container
COPY target/assessment-*.jar app.jar

# Expose the port that the application will run on
EXPOSE 8080

# Run the JAR file
ENTRYPOINT ["java", "-jar", "app.jar"]
