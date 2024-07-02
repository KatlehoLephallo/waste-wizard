# Waste Sorting Mobile Application

## Overview

This project is a waste sorting mobile application developed for Enviro365. The application promotes sustainable waste management practices by providing guidance on proper waste disposal techniques and encouraging recycling habits.

## Features

- **Waste Category Lookup:** Users can look up different waste categories to understand how to dispose of them properly.
- **Disposal Guidelines Retrieval:** The application provides guidelines for disposing of different types of waste.
- **Recycling Tips Display:** The application provides tips for recycling different types of waste.

## Technologies Used

- **Spring Boot:** Used for creating the RESTful web service.
- **Spring HATEOAS:** Used for creating a hypermedia-driven RESTful web service.
- **JUnit and Mockito:** Used for testing the application.

## Endpoints

The application provides several endpoints for interacting with waste categories, disposal guidelines, and recycling tips:

- `GET /waste-wizard/waste-categories`: Retrieves all waste categories.
- `GET /waste-wizard/waste-categories/{id}`: Retrieves a specific waste category by its ID.
- `POST /waste-wizard/waste-categories`: Creates a new waste category.
- `PUT /waste-wizard/waste-categories/{id}`: Updates an existing waste category.
- `DELETE /waste-wizard/waste-categories/{id}`: Deletes a specific waste category.

Similar endpoints are provided for disposal guidelines and recycling tips.

## How to Run

1. Clone the repository: `git clone https://github.com/KatlehoLephallo/waste-wizard.git`
2. Navigate to the project directory: `cd KatlehoLephallo`
3. Build the project: `mvn clean install`
4. Run the application: `java -jar target/KatlehoLephallo-0.0.1-SNAPSHOT.jar`

## Testing

Unit tests have been written for the controllers and can be run with the `mvn test` command.

## Future Improvements

- Add user authentication and authorization.
- Add more detailed error handling.

## License

This project is licensed under the terms of the MIT license. See the LICENSE file for details.
