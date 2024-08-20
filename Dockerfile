#############################################
###            Version 0.0.3              ###
#############################################

            # Builder stage
# Use an official Eclipse Temurin image with JDK 21.0.2_13 and Ubuntu Jammy (22.04) as the base image
FROM eclipse-temurin:21.0.2_13-jdk-jammy AS builder

# Set the working directory in the Docker container to /waste-wizard
WORKDIR /waste-wizard

# Copy the .mvn directory, mvnw file, and pom.xml file from your project into the Docker image
COPY .mvn/ .mvn
COPY mvnw pom.xml ./

# Download your project's dependencies
RUN ./mvnw dependency:go-offline

# Copy your application's source code (in the src directory) into the Docker image
COPY src ./src

# Build your project and package it as a JAR file
RUN ./mvnw clean install

            # Final stage
# Use an official Eclipse Temurin image with JRE 21.0.2_13 and Ubuntu Jammy (22.04) as the base image
FROM eclipse-temurin:21.0.2_13-jre-jammy AS final

# Set the working directory in the Docker container to /waste-wizard
WORKDIR /waste-wizard

# Expose port 8080 of the Docker container
EXPOSE 8080

# Copy the JAR file from the builder stage into the final Docker image
COPY --from=builder /waste-wizard/target/waste-wizard-0.0.3.jar /waste-wizard/

# Run your application when the Docker container starts up
ENTRYPOINT ["java", "-jar", "/waste-wizard/waste-wizard-0.0.3.jar"]




#############################################
###            Version 0.0.2              ###
#############################################

# Use an official Eclipse Temurin runtime as a parent image
# FROM eclipse-temurin:21.0.2_13-jdk-jammy

# Set the working directory in the container to /waste-wizard
# WORKDIR /waste-wizard

# Copy the .mvn directory and the Maven Wrapper (mvnw) and pom.xml files into the Docker image
# COPY .mvn/ .mvn
# COPY mvnw pom.xml ./

# Run the Maven Wrapper command to download the project's dependencies
# RUN ./mvnw dependency:go-offline

# Copy the src directory (which contains your application's source code) into the Docker image
# COPY src ./src

# Run the Maven Wrapper command to start the Spring Boot application when the Docker container starts up
# CMD ["./mvnw", "spring-boot:run"]




#############################################
###            Version 0.0.1              ###
#############################################

# Use an official OpenJDK runtime as a parent image
# FROM openjdk:17-jdk-slim

# Set the working directory in the container to /waste-wizard
# WORKDIR /waste-wizard

# Copy the waste-wizard-0.0.1.jar file from your libs directory into the /waste-wizard directory in the Docker image
# COPY ./libs/waste-wizard-0.0.1.jar /waste-wizard/

# Run waste-wizard-0.0.1.jar when the Docker container starts up
# CMD ["java","-jar","/waste-wizard/waste-wizard-0.0.1.jar"]
