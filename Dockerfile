# Use an official Eclipse Temurin runtime as a parent image
FROM eclipse-temurin:21.0.2_13-jdk-jammy

# Set the working directory in the container to /waste-wizard
WORKDIR /waste-wizard

# Copy the .mvn directory and the Maven Wrapper (mvnw) and pom.xml files into the Docker image
COPY .mvn/ .mvn
COPY mvnw pom.xml ./

# Run the Maven Wrapper command to download the project's dependencies
RUN ./mvnw dependency:go-offline

# Copy the src directory (which contains your application's source code) into the Docker image
COPY src ./src

# Run the Maven Wrapper command to start the Spring Boot application when the Docker container starts up
CMD ["./mvnw", "spring-boot:run"]




#############################################
###           Previous Version            ###
#############################################

# Use an official OpenJDK runtime as a parent image
# FROM openjdk:17-jdk-slim

# Set the working directory in the container to /waste-wizard
# WORKDIR /waste-wizard

# Copy the waste-wizard-0.0.1.jar file from your libs directory into the /waste-wizard directory in the Docker image
# COPY ./libs/waste-wizard-0.0.1.jar /waste-wizard/

# Run waste-wizard-0.0.1.jar when the Docker container starts up
# CMD ["java","-jar","/waste-wizard/waste-wizard-0.0.1.jar"]
