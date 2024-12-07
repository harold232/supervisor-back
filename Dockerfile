# Use an official OpenJDK runtime as a parent image
FROM openjdk:17-jdk-slim

# Set the working directory
WORKDIR /app

# Copy the current directory contents into the container at /app
COPY . /app

# Grant execute permissions to the mvnw script
RUN chmod +x ./mvnw

# Build the application
RUN ./mvnw -B -DskipTests clean package

# Make port 8080 available to the world outside this container
EXPOSE 8080

# Run the application
CMD ["java", "-jar", "target/supervisor-back.jar"]