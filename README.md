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


### Explanation of Folders:
  
src/
│ 
├── main/
│ ├── java/ 
│ │ └── com/ 
│ │ └── api/ 
│ │ └── contentctrl/ 
│ │ ├── ContentctrlApplication.java # Main class of the Spring Boot application 
│ │ ├── config/ # Security, JWT, and Swagger configurations 
│ │ ├── controller/ # REST API controllers 
│ │ ├── model/ # Data models, such as the User 
│ │ └── service/ # Business logic, such as user manipulation 
│ ├── resources/ 
│ │ ├── application.properties # Spring Boot configuration 
│ │ └── static/ # Static files (if necessary) 
│ └── test/ 
│ └── java/ 
│ └── com/ 
│ └── api/ 
│ └── contentctrl/ 
│ ├── service/ # Unit tests for services 
│ └── controller/ # Tests for the API controllers

 

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

Alternatively, you can run the ContentctrlApplication class directly from your IDE.

4. **Access the application:**:
5. The API will be available at http://localhost:8080. 

6. **Test the API:**: 
- **Access the Swagger API documentation at: http://localhost:8080/swagger-ui/.
- **The Swagger interface allows you to interact with the API directly from the browser and test the endpoints.

Usage Examples
- **Endpoint: Login
- **Method: POST
- **URL: /api/auth/login
- **Description: Logs the user in and returns a JWT token if authentication is successful.
- **Request Body:
   json 
   {
     "username": "user",
     "password": "password"
   }
- **Response:
   json
   {
     "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
   }
  
- **Endpoint: Register User
- **Method: POST
- **URL: /api/auth/register
- **Description: Registers a new user in the system.
- **Request Body:
   json 
   {
     "username": "new_user",
     "password": "new_password"
   }
- **Protected Endpoint
- **Method: GET
- **URL: /api/protected
- **Description: A protected endpoint that can only be accessed with a valid JWT token.
- **Headers:
- **makefile
 
### Authorization: Bearer <token>
- **Swagger Documentation
- **Swagger is integrated into the project to provide an interactive interface for testing the API endpoints.

Access the Swagger interface at: http://localhost:8080/swagger-ui/.
Contributing
If you want to contribute to this project, follow these steps:

### Fork this repository.
- **Create a new branch: git checkout -b my-contribution.
- **Make the desired changes.
- **Commit and push to your branch: git push origin my-contribution.
- **Open a pull request for review.
- **License
- **This project is licensed under the MIT License. See the LICENSE file for more details.

### Javadoc
In addition to all the documentation provided in the README.md, the class, method, and package documentation can be accessed through Javadoc. To generate the Javadoc documentation, use the following command:

 
mvn javadoc:javadoc
You can then access the generated documentation in the target/site/apidocs directory.

 



