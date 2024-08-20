# Use an official OpenJDK runtime as a parent image
FROM openjdk:17-jdk-alpine

# Set the working directory in the container to /waste-wizard
WORKDIR /waste-wizard

# Copy the waste-wizard-0.0.1.jar file from your libs directory into the /waste-wizard directory in the Docker image
COPY ./libs/waste-wizard-0.0.1.jar /waste-wizard/

# Run waste-wizard-0.0.1.jar when the Docker container starts up
CMD ["java","-jar","/waste-wizard/waste-wizard-0.0.1.jar"]
