# Use OpenJDK 23 base image instead of 17
FROM openjdk:23-jdk-slim

# Set the working directory in the container
WORKDIR /app

# Install Maven and any additional required dependencies
RUN apt-get update && apt-get install -y \
    maven \
    && rm -rf /var/lib/apt/lists/*

# Copy the current directory contents into the container
COPY . /app

# Build the project using Maven
RUN mvn clean install

# Expose the port the app will run on (change if needed)
EXPOSE 8080

# Command to run the Java application
CMD ["java", "-jar", "target/game.jar"]
