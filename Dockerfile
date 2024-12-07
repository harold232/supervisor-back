# Use an official OpenJDK runtime as a parent image
FROM openjdk:17-jdk-slim

# Set the working directory
WORKDIR /app

# Copy the current directory contents into the container at /app
COPY . /app

# Build the application
RUN ./mvnw -DoutputFile=target/mvn-dependency-list.log -B -DskipTests clean dependency:list install

# Make port 8080 available to the world outside this container
EXPOSE 8080

# Run the application
CMD ["java", "-jar", "target/supervisor-back.jar"]