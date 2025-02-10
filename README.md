# Contentctrl - Spring Boot API with JWT and Swagger

## Project Description

**Contentctrl** is a backend system built using **Spring Boot**, designed to provide a RESTful API that implements authentication via **JWT** (JSON Web Tokens). The application also features automatic API documentation using **Swagger** and code documentation via **Javadoc**.

The application is created to manage users and provide a secure system to control access to resources based on authentication and authorization. With JWT implementation, the system ensures that only authenticated users can access certain protected endpoints.

## Features

- **JWT Authentication**: Login implementation and JWT token generation for user authentication.
- **API Security**: Uses Spring Security to ensure route protection, allowing only authenticated users to access certain resources.
- **Swagger Documentation**: Automatically documents the API, providing an easy-to-use interface for navigating and testing endpoints.
- **User Management**: Creation and management of users, including login and registration operations.

## Technologies Used

- **Spring Boot**: The main framework used to build the RESTful API.
- **Spring Security**: For security management, including authentication and authorization.
- **JWT (JSON Web Token)**: For generating authentication tokens.
- **Swagger**: For automatic API documentation.
- **Javadoc**: For generating code documentation.

## Project Structure

The basic project structure is as follows:

src/ │ ├── main/ │ ├── java/ │ │ └── com/ │ │ └── api/ │ │ └── contentctrl/ │ │ ├── ContentctrlApplication.java # Main Spring Boot application class │ │ ├── config/ # Security, JWT, and Swagger configurations │ │ ├── controller/ # REST controllers for API endpoints │ │ ├── model/ # Data models, such as the User │ │ └── service/ # Business logic, such as user management │ ├── resources/ │ │ ├── application.properties # Spring Boot configuration │ │ └── static/ # Static files (if needed) │ └── test/ │ └── java/ │ └── com/ │ └── api/ │ └── contentctrl/ │ ├── service/ # Unit tests for services │ └── controller/ # Tests for API controllers



### Key Components:

- **ContentctrlApplication.java**: The main class that starts the Spring Boot application.
- **SecurityConfig.java**: Spring Security configurations, including JWT setup.
- **UserService.java**: The service that contains business logic for user operations like login and registration.
- **UserController.java**: The REST controller that handles user-related requests such as login, registration, etc.
- **JwtTokenUtil.java**: A utility class for generating and validating JWT tokens.
- **SwaggerConfig.java**: The Swagger configuration to generate automatic API documentation.

## Dependencies

The key dependencies in this project are:

- **Spring Boot**: The foundation for the project development.
- **Spring Security**: For security control.
- **JWT (Java JWT)**: For implementing JWT tokens.
- **Swagger (Springfox)**: For API documentation.

The dependencies can be found in the `pom.xml` file.

## Running the Project

To run the project locally, follow these steps:

1. **Clone the repository**:
   ```bash
   git clone https://github.com/your-username/contentctrl.git

2. **Install the dependencies (if needed, run Maven or Gradle):**:
   mvn install

3. **Run the application: To run the application locally:**:
  mvn spring-boot:run







