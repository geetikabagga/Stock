# Use an official Java runtime as the base image
FROM openjdk:17-jdk-alpine

# Set the working directory inside the container
WORKDIR /stock

# Copy the JAR file into the container
COPY target/stock-1.0-SNAPSHOT.jar stock.jar

# Expose the application port
EXPOSE 8080

# Run the application
CMD ["java", "-jar", "stock.jar"]
